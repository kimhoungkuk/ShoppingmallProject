<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<style>
button{
border:0;
outline:0;
}

</style>

 <script>
 $(document).ready(function() {
	 
	 $('#colorSelector').ColorPicker({
			color: '#0000ff',
			onShow: function (colpkr) {
				$(colpkr).fadeIn(500);
				return false;
			},
			onHide: function (colpkr) {
				$(colpkr).fadeOut(500);
				return false;
			},
			onChange: function (hsb, hex, rgb) {
				
				$('#colorSelector div').css('backgroundColor', '#' + hex);
				$('#getcode').val(hex);
			}
		});

	});

</script> 
상품 옵션 --------------------------------------------------------<br>
<div class="col-sm-10">
	<form action="productOptionRegister" method="post">
<!-- http://www.eyecon.ro/colorpicker/#about -->


<table class="table table-bordered">		
			        <colgroup>
			            <col width="15%">
			            <col width="85%">  
			        </colgroup>
			        <tbody>				
					<tr>
						<th class="col-md-2">상품코드</th> 
						<td>
						<input type="text" name="prdtCode" value="123" class="form-control">
						</td>
					</tr>
					<tr>
						<th>상품컬러코드 및 색상</th>
						<td>
							코드<button id="colorSelector"><div></div></button> 
							 <input id="getcode" name="prdtColorCode" type="text" maxlength="6" size="6" readonly> 
							컬러 이름<input type="text" name="colorName">
							
						</td>
					</tr>
					<tr>
						<th>사이즈</th>
						<td>
							<input type="text" name="prdtSize" class="form-control">
						</td>
					</tr>
					<tr>
						<th>재고</th>
						<td> 
						   <input type="number" name="prdtLaveCount" class="form-control">
						</td>
					</tr>	
					<tr>
						<th>생성자 이름</th>
						<td> 
						  <input type="text" name="rgsId" value="idddd" class="form-control" readonly>
						  <!-- 대표적으로 readonly 와 disabled  readonly는 form으로 넘길수있다 disabled는 안딤 --> 
						</td>
					</tr>
					
					
					
					</tbody>																					
				</table>
				<input type="submit" value="드으응록">
</form>
</div>
