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
상품 옵션 수정수정수정수정이 --------------------------------------------------------
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
					<th class="col-md-2">상품코드</th>
					<td><input type="text" name="prdtCode"
						value="${modify.prdtCode }" class="form-control" readonly></td>
				</tr>
				<tr>
					<th>상품컬러코드 및 색상</th>
					<td>
						코드<button id="colorSelector"><div></div></button> 
						<input id="getcode"	type="text" name="prdtColorCode" value="${modify.prdtColorCode }" maxlength="6" size="6" readonly> 
						컬러 이름<input type="text" name="colorName" value="${modify.colorName }">
					</td>
				</tr>
				<tr>
					<th>사이즈</th>
					<td><input type="text" name="prdtSize"
						value="${modify.prdtSize }" class="form-control"></td>
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="number" name="prdtLaveCount"
						value="${modify.prdtLaveCount }" class="form-control"></td>
				</tr>
				<tr>
					<th>생성자 이름</th>
					<td>${modify.rgsId } <!-- 대표적으로 readonly 와 disabled  readonly는 form으로 넘길수있다 disabled는 안딤 -->
					</td>
				</tr>
				<tr>
					<th>생성일</th>
					<td>${modify.regtDtm }</td>
				</tr>
				<tr>
					<th>수정자/수정일</th>
					<c:choose>
						<c:when test="${modify.modDtm eq null}">
							<td>아직 수정하지않았습니다
						</c:when>
						<c:when test="${modify.modDtm != null }">
							<td>${modify.modDtm }
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${modify.modId eq null}">
							아직 수정자가 없음</td>
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
		<input type="submit" value="수정">
		
	</form>
	
	
</div>
