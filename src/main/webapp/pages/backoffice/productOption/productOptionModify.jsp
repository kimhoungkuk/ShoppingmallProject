<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<style>
input{
width:70px;
}
.rgsmod{
font-size:13px;
}
</style>
<script>

function goDelete(optionSeq, prdtcode){
	$.ajax({
		url: '/admin/pod/' + optionSeq
		, type: 'DELETE'
		, dataType: 'JSON'
		, success: function(data) {
			if (data && data.resultYn == 'Y') {
				location.href = '/admin/pomForm/'+ prdtcode;
				alert(data.resultMsg);
			} else {
				alert('error');				
			}
		}, error: function(xhr, status, text) {
			alert(text);
		}
	});
}

$(document).ready(function() {
	//���۰� ���ÿ� ���� ����
	$('.colorset').click(function(){
		$(this).children('.color').attr("value",'#'+$(this).children('.getcode').val());
	}); 
	$('.colorset').trigger('click');
	
	
	// ��ǰ�ڵ� ����!
	 $('.codeparent').change(function(){
		 $('.codechild').val($('.codeparent').val());
	 });
	//colorpicker
	 $('.colorset').change(function(){
		
		$(this).children('.color').attr("value",$(this).children('.color').val());
		$(this).children('.getcode').val($(this).children('.color').val().replace("#",""));
		
	}); 

	});
	
</script>
��ǰ �ɼ� ������������������ --------------------------------------------------------

<form id="deleteForm"></form>

<br>
<div class="col-sm-10">
	<form action="/admin/productOptionModify" method="post">
		 
		<table class="table table-bordered">
		<colgroup>
				<col width="10%">
		</colgroup>
				<tr>
					<th>
					��ǰ�ڵ�
					</th>
					<td>
					<input type="text" name="modifycode" class="codeparent form-control" value="${modifycode}">
					</td>
				</tr>
		</table>
		
		<table class="table table-bordered">
			<tbody>
			
			<c:forEach items="${modify }" var="modi" >
				<tr>
					<th rowspan="2" colspan="3">�ɼ�
					<input type="hidden" name="prdtCode" class="codechild" value="${modi.prdtCode }">
					<input type="hidden" name="modId" value="session">
					<!-- ������ -->
					<!-- <input type="hidden" name="mdprdtCode" value="${modi.prdtCode }">
					<input type="hidden" name="mdprdtColorCode" value="${modi.prdtColorCode }">
					<input type="hidden" name="mdprdtSize" value="${modi.prdtSize }">  -->
					<input type="hidden" name="optionSeq" value="${modi.optionSeq }">
					</th>
					<td rowspan="2">
					<div class="colorset" style="float:left">
					<input type="color" class="color">
					�����ڵ�
					<input class="getcode" name="prdtColorCode" type="text" value="${modi.prdtColorCode }" readonly> 
					</div>
						���� �̸�<input type="text" name="colorName" value="${modi.colorName }">
						������<select name="prdtSize"> 
							<option value="${modi.prdtSize }">������ ����</option>
							<option value="small">Small</option>
							<option value="medium">Medium</option>
							<option value="large">Large</option>
							<option value="free">Free</option>
						</select> 
						���<input type="number" name="prdtLaveCount" value="${modi.prdtLaveCount }">
						<%-- <a href="javascript:goDelete('${modi.prdtCode }', '${modi.prdtColorCode }', '${modi.prdtSize }');"><input type="button" value="����"></a> --%>
						<a href="javascript:goDelete('${modi.optionSeq }','${modi.prdtCode }');"><input type="button" value="����"></a>
					</td>
						<th class="rgsmod">������ <br> ������</th>
						<td class="rgsmod">${modi.rgsId } <br> ${modi.regtDtm }</td>
					
				</tr>
					

				<tr class="rgsmod">
					<th>������ <br>������</th>
					<td>
					<c:choose>
						<c:when test="${modi.modId eq null}">
							���� �̷� ����
						</c:when>
						<c:when test="${modi.modId != null }">
							 ${modi.modId } <br> 
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${modi.modDtm eq null}">
							
						</c:when>
						<c:when test="${modi.modDtm != null }">
							 ${modi.modDtm } 
						</c:when>
					</c:choose>
					</td>
				</tr>
				
			</c:forEach>
			</tbody>
			
		</table>
		
		<input type="submit" value="����">
		
	</form>

</div>
