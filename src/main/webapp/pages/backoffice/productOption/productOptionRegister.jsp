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
		//�ɼ��߰� 
		$('.addoption').click(function() {

			$(".after:last").after($('.after:first').clone(true));

		});
		//colorpicker
		 $('.colorset').change(function(){
			$(this).children('.color').attr("value",$(this).children('.color').val());
			$(this).children('.getcode').val($(this).children('.color').val().replace("#",""));
			
		}); 
		 
		// ��ǰ�ڵ� ����!
		 $('.codeparent').change(function(){
			 $('.codechild').val($('.codeparent').val());
		 });

	});
</script>
<p style="font-size: 15pt">��ǰ �ɼ� > ��� �� porFrom</p>
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
					<th>��ǰ �ڵ�</th>
					<td>
					<input type="text" class="codeparent form-control">
					</td>
				</tr>

				<tr class="after">
					<th>�ɼ� 
					<input class="addoption" type="button" value="�ɼ��߰�">
					</th>
					
					<td>
					<div class="colorset" style="float:left">
					<input type="color" class="color">
					�����ڵ�
					<input class="getcode" name="prdtColorCode" type="text" readonly> 
					</div>
					

					���� �̸�
					
					<input type="text" name="colorName">
					

				
					������

					
						<select name="prdtSize">
							<option>������ ����</option>
							<option value="small">Small</option>
							<option value="medium">Medium</option>
							<option value="large">Large</option>
							<option value="free">Free</option>
						</select>
					
				
					���
					<input type="number" name="prdtLaveCount">
					<input type="hidden" name="prdtCode" class="codechild">
					<input type="hidden" name="rgsId" value="sieunlim">
					 
					</td>
				</tr> 



			</tbody>
		</table>
		<input type="submit" class="btn btn-default pull-right" value="���">
	</form>
</div>