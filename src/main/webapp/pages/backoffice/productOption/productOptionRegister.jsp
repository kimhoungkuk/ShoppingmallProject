<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.after input{
width:80px;
}
</style>
<script>
$(document).ready(function() {
		//옵션추가 
		$('.addoption').click(function() {

			$(".after:last").after($('.after:first').clone(true));

		});
		//colorpicker
		 $('.colorset').change(function(){
			$(this).children('.color').attr("value",$(this).children('.color').val());
			$(this).children('.getcode').val($(this).children('.color').val().replace("#",""));
			
		}); 
		 
		// 상품코드 기준!
		 $('.codeparent').change(function(){
			 $('.codechild').val($('.codeparent').val());
		 });

	});
</script>
<p style="font-size: 15pt">상품 옵션 > 등록 폼 porFrom</p>
<br>
<div class="col-sm-10">
	<form action="productOptionRegister" method="post">

		<table class="table table-bordered">
			<colgroup>
				<col width="15%">
				<col width="85%">
			</colgroup>
			<tbody>
				<tr>
					<th>상품 코드</th>
					<td>
					<input type="text" class="codeparent form-control">
					</td>
				</tr>

				<tr class="after">
					<th>옵션 
					<input class="addoption" type="button" value="옵션추가">
					</th>
					
					<td>
					<div class="colorset" style="float:left">
					<input type="color" class="color">
					색상코드
					<input class="getcode" name="prdtColorCode" type="text" readonly> 
					</div>
					

					색상 이름
					
					<input type="text" name="colorName">
					

				
					사이즈

					
						<select name="prdtSize">
							<option>사이즈 선택</option>
							<option value="small">Small</option>
							<option value="medium">Medium</option>
							<option value="large">Large</option>
							<option value="free">Free</option>
						</select>
					
				
					재고
					<input type="number" name="prdtLaveCount">
					<input type="hidden" name="prdtCode" class="codechild">
					<input type="hidden" name="rgsId" value="sieunlim">
					 
					</td>
				</tr> 



			</tbody>
		</table>
		<input type="submit" class="btn btn-default pull-right" value="등록">
	</form>
</div>