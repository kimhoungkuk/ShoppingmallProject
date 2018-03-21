<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

	<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>NO</th>
				<th>할인명</th>
				<th>할인가격</th>
				<th>할인타입</th>
				<th>할인시작일</th>
				<th>할인종료일</th>		
				<th>상품등록자</th>		
				<th>상품등록일</th>			
			</tr>
			<c:forEach items="${discountList}" var="discount" varStatus="status">
				<tr>
					<td>
						${status.count}
					</td>
					<td>
						${discount.dcntName}
					</td>
					<td>
						<fmt:formatNumber value="${discount.dcntPrice}" pattern="#,###" />
					</td> 
					<td>
						${discount.dcntType}
					</td>
					<td>
						<fmt:formatDate value="${discount.dcntStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${discount.dcntEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${discount.dcntRegId}
					</td>										    								      
					<td>
						<fmt:formatDate value="${discount.dcntRegDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>										    								      
				</tr>  
			</c:forEach>
		</table>

		<a  href="/admin/discount/discountRegisterForm" class="btn btn-default pull-right">작성하기</a>

         <c:import url="/admin/common/paging" charEncoding="utf-8">
             <c:param name="startPageNo" value="${discount.startPageNo}" />
             <c:param name="endPageNo" value="${discount.endPageNo}" />
             <c:param name="firstPageNo" value="${discount.firstPageNo}" />
             <c:param name="nextPageNo" value="${discount.nextPageNo}" />
             <c:param name="pageNo" value="${discount.pageNo}" />
             <c:param name="pagegUrl" value="${discount.pagegUrl}" />
         </c:import>	
         
	</div>