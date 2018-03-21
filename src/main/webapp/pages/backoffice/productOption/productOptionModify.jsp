<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>


<script>
$(document).ready(function() {
	 $('#colorSelector div').css('backgroundColor', '#' + '${modify.prdtColorCode }');
	 
	 $('#colorSelector').ColorPicker({
			color: '${modify.prdtColorCode }',
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
��ǰ �ɼ� ������������������ --------------------------------------------------------
<br>
<div class="col-sm-10">
	<form action="/admin/productOptionModify" method="post">



		<table class="table table-bordered">
			<colgroup>
				<col width="15%">
				<col width="85%">
			</colgroup>
			<tbody>
				<tr>
					<th class="col-md-2">��ǰ�ڵ�</th>
					<td><input type="text" name="prdtCode"
						value="${modify.prdtCode }" class="form-control" readonly></td>
				</tr>
				<tr>
					<th>��ǰ�÷��ڵ� �� ����</th>
					<td>
						�ڵ�<button id="colorSelector"><div></div></button> 
						<input id="getcode"	type="text" name="prdtColorCode" value="${modify.prdtColorCode }" maxlength="6" size="6" readonly> 
						�÷� �̸�<input type="text" name="colorName" value="${modify.colorName }">
					</td>
				</tr>
				<tr>
					<th>������</th>
					<td><input type="text" name="prdtSize"
						value="${modify.prdtSize }" class="form-control"></td>
				</tr>
				<tr>
					<th>���</th>
					<td><input type="number" name="prdtLaveCount"
						value="${modify.prdtLaveCount }" class="form-control"></td>
				</tr>
				<tr>
					<th>������ �̸�</th>
					<td>${modify.rgsId } <!-- ��ǥ������ readonly �� disabled  readonly�� form���� �ѱ���ִ� disabled�� �ȵ� -->
					</td>
				</tr>
				<tr>
					<th>������</th>
					<td>${modify.regtDtm }</td>
				</tr>
				<tr>
					<th>������/������</th>
					<c:choose>
						<c:when test="${modify.modDtm eq null}">
							<td>���� ���������ʾҽ��ϴ�
						</c:when>
						<c:when test="${modify.modDtm != null }">
							<td>${modify.modDtm }
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${modify.modId eq null}">
							���� �����ڰ� ����</td>
						</c:when>
						<c:when test="${modify.modId != null }">
							${modify.modId }
							
							</td>
						</c:when>
					</c:choose>
				</tr>


			</tbody>
			
		</table>
		<input type="hidden" name="modId" value="session"> 
		<input type="submit" value="����">
		
	</form>
	
	
</div>
