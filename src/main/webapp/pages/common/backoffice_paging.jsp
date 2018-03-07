<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<nav class="text-center">
  <ul class="pagination pagination-lg">
    <li><a href="#" onClick="javascript:goPage('${param.firstPageNo}');"><span aria-hidden="true">«</span><span class="sr-only">Previous</span></a></li>
  	<c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
	    <c:choose>
           <c:when test="${i eq param.pageNo}"> <li class="active"><a href="${param.pagegUrl}${i}">${i}</a></li></c:when>
           <c:otherwise><li><a href="${param.pagegUrl}${i}">${i}</a></li></c:otherwise>
        </c:choose>
    </c:forEach>
    <li><a href="#" onClick="javascript:goPage('${param.nextPageNo}');"><span aria-hidden="true">»</span><span class="sr-only">Next</span></a></li>
  </ul>
</nav>