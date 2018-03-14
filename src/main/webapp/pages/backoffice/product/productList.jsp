<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
function goRegister(){
	var encodingUrl = encodeURIComponent("${product.pagegUrl}${product.pageNo}");
	document.location.href = "/admin/product/productRegisterForm?listUrl="+encodingUrl;
}

function goModify(prdtCode){
	var encodingUrl = encodeURIComponent("${product.pagegUrl}${product.pageNo}");
	document.location.href = "/admin/product/productModify/"+prdtCode+"?listUrl="+encodingUrl;
}

</script>
	<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>상품코드</th>
				<th>상품한글명</th>
				<th>상품영문명</th>
				<th>상품가격</th>
				<th>상품전시여부</th>		
				<th>상품등록자</th>		
				<th>상품등록일</th>			
			</tr>
			<c:forEach items="${productList}" var="product" varStatus="prdtIdx">
				<tr>
					<td>
						<a href="javascript:goModify('${product.prdtCode}');">
							${product.prdtCode}
						</a>
					</td>
					<td>
						${product.prdtKorName}
					</td>
					<td>
						${product.prdtEngName}
					</td> 
					<td>
						<fmt:formatNumber value="${product.prdtSellPrice}" pattern="#,###" />
					</td>
					<td>
						${product.prdtDispYn}
					</td>
					<td>
						${product.prdtRegId} 
					</td>
					<td>
						<fmt:formatDate value="${product.prdtRegDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>										    								      
				</tr>  
			</c:forEach>
		</table>

		<a href="javascript:goRegister();" class="btn btn-default pull-right">작성하기</a>

         <c:import url="/admin/common/paging" charEncoding="utf-8">
             <c:param name="startPageNo" value="${product.startPageNo}" />
             <c:param name="endPageNo" value="${product.endPageNo}" />
             <c:param name="firstPageNo" value="${product.firstPageNo}" />
             <c:param name="nextPageNo" value="${product.nextPageNo}" />
             <c:param name="pageNo" value="${product.pageNo}" />
             <c:param name="pagegUrl" value="${product.pagegUrl}" />
         </c:import>	
         
	</div>