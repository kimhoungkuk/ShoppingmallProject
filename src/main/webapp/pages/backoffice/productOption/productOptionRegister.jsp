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
��ǰ �ɼ� --------------------------------------------------------<br>
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
						<th class="col-md-2">��ǰ�ڵ�</th> 
						<td>
						<input type="text" name="prdtCode" value="123" class="form-control">
						</td>
					</tr>
					<tr>
						<th>��ǰ�÷��ڵ� �� ����</th>
						<td>
							�ڵ�<button id="colorSelector"><div></div></button> 
							 <input id="getcode" name="prdtColorCode" type="text" maxlength="6" size="6" readonly> 
							�÷� �̸�<input type="text" name="colorName">
							
						</td>
					</tr>
					<tr>
						<th>������</th>
						<td>
							<input type="text" name="prdtSize" class="form-control">
						</td>
					</tr>
					<tr>
						<th>���</th>
						<td> 
						   <input type="number" name="prdtLaveCount" class="form-control">
						</td>
					</tr>	
					<tr>
						<th>������ �̸�</th>
						<td> 
						  <input type="text" name="rgsId" value="idddd" class="form-control" readonly>
						  <!-- ��ǥ������ readonly �� disabled  readonly�� form���� �ѱ���ִ� disabled�� �ȵ� --> 
						</td>
					</tr>
					
					
					
					</tbody>																					
				</table>
				<input type="submit" value="��������">
</form>
</div>





		
		
		<!-- !!�ɼ�!!
		��ǰ�÷� : <select name="prdtColorCode" multiple>
					<option value="b">black</option>
					<option value="r">red</option>
					<option value="w">white</option>
				</select>
		<br>
		�÷����� html : <input type="color">
		<br>
		�÷����� plugin : <button id="colorSelector"></button>	<input id="getcode" type="text">
		
		  <br>
		
		�÷��� : <input type="text" name="colorName"> <br>
	
		��ǰ ������ s m l : <input type="text" name="prdtSize"> <br>
		
		��ǰ ��� : <input type="number" name="prdtLaveCount"> <br>
		
		������ ���� ���̵� �޾ƿ��� <input type="hidden" name="rgsId" value="idddd"> <br> -->
		
		�������� ����Ʈ & ������ ���� & ������̿��� ���ʻ����Ϸ� <br>
		������/�����ڴ� ����Ʈ & ������� ----> ����Ʈ�κ����ֱ�<br>
		  <br>
		
		

	

