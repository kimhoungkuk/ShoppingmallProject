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

	});
	
</script>
상품 옵션 수정수정수정수정이 --------------------------------------------------------

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
					<input type="text" class="codeparent form-control" value="${modifydelete}">
					</td>
				</tr>
		</table>
		
		<table class="table table-bordered">
			<tbody>
			
			<c:forEach items="${modify }" var="modi" >
				<tr>
					<th rowspan="2" colspan="3">옵션
					<input type="hidden" name="prdtCode" class="codechild" value="${modi.prdtCode }">
					<input type="hidden" name="modId" value="session">
					<!-- 수정용 -->
					<input type="hidden" name="mdprdtCode" value="${modi.prdtCode }">
					<input type="hidden" name="mdprdtColorCode" value="${modi.prdtColorCode }">
					<input type="hidden" name="mdprdtSize" value="${modi.prdtSize }">
					</th>
					<td rowspan="2">
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
					</td>
						<th class="rgsmod">생성자 <br> 생성일</th>
						<td class="rgsmod">${modi.rgsId } <br> ${modi.regtDtm }</td>
					
				</tr>
					

				<tr class="rgsmod">
					<th>수정자 <br>수정일</th>
					<td>
					<c:choose>
						<c:when test="${modi.modId eq null}">
							수정 이력 없음
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
		
		<input type="submit" value="수정">
		
	</form>

</div>
