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
	//시작과 동시에 색갈 변경
	$('.colorset').click(function(){
		$(this).children('.color').attr("value",'#'+$(this).children('.getcode').val());
	}); 
	$('.colorset').trigger('click');
	
	
	// 상품코드 기준!
	 $('.codeparent').change(function(){
		 $('.codechild').val($('.codeparent').val());
	 });
	//colorpicker
	 $('.colorset').change(function(){
		
		$(this).children('.color').attr("value",$(this).children('.color').val());
		$(this).children('.getcode').val($(this).children('.color').val().replace("#",""));
		
	}); 
		//옵션추가 
		$('.addoption').click(function() {

			$(".after:last").after($('.after:first').clone(true));
			$(".after:last .addoption").attr('type','hidden');
			$(".after:last .optionseq").attr('value','0');
			$(".after:last .delete").attr('type','hidden');
			$(".after:last .rgsmod").hide();
			$(".after:last .deleteoption").attr('type','button');
			
		});
		
		//옵션취소
	$('.deleteoption').click(function(){
			
			$(this).parents('.after:last').remove();
		});

	});
	
</script>
상품 옵션 수정수정수정수정이 --------------------------------------------------------

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
					상품코드
					</th>
					<td>
					<input type="text" name="modifycode" class="codeparent form-control" value="${modifycode}">
					</td>
				</tr>
		</table>
		
		<table class="table table-bordered">
			<colgroup>
				<col width="10%">
				<col width="50%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<tbody>			
			<c:forEach items="${modify }" var="modi" >
				<tr class="after">
					<th>옵션
					<!-- 추가 insert -->
					<input class="addoption" type="button" value="옵션추가">
					<input class="deleteoption" type="hidden" value="취소">
					<!-- hidden -->
					<input type="hidden" name="prdtCode" class="codechild" value="${modi.prdtCode }">
					<input type="hidden" name="modId" value="session">
					<input class="optionseq" type="hidden" name="optionSeq" value="${modi.optionSeq }">
					<input type="hidden" name="rgsId" value="sieun">
					</th>
					<td>
					<div class="colorset" style="float:left">
					<input type="color" class="color">
					색상코드
					<input class="getcode" name="prdtColorCode" type="text" value="${modi.prdtColorCode }" readonly> 
					</div>
						색상 이름<input type="text" name="colorName" value="${modi.colorName }">
						사이즈<select name="prdtSize"> 
							<option value="${modi.prdtSize }">사이즈 선택</option>
							<option value="small">Small</option>
							<option value="medium">Medium</option>
							<option value="large">Large</option>
							<option value="free">Free</option>
						</select> 
						재고<input type="number" name="prdtLaveCount" value="${modi.prdtLaveCount }">
						<%-- <a href="javascript:goDelete('${modi.prdtCode }', '${modi.prdtColorCode }', '${modi.prdtSize }');"><input type="button" value="삭제"></a> --%>
						<a href="javascript:goDelete('${modi.optionSeq }','${modi.prdtCode }');"><input class="delete" type="button" value="삭제"></a>
					
						<th>생성자<br>생성일</th>
						<td><div class="rgsmod">${modi.rgsId }<br>${modi.regtDtm }</div></td>
				    <th>수정자<br>수정일</th>
					<td>
					<div class="rgsmod">
					<c:choose>
						<c:when test="${modi.modId eq null}">
							수정 이력 없음
						</c:when>
						<c:when test="${modi.modId != null }">
							 ${modi.modId }  <br>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${modi.modDtm eq null}">
							
						</c:when>
						<c:when test="${modi.modDtm != null }">
							 ${modi.modDtm } 
						</c:when>
					</c:choose>
					</div>
					</td>
					
					</tr>
				
			</c:forEach>
			</tbody>
			
		</table>
		
		<input type="submit" value="수정">
		
	</form>

</div>
