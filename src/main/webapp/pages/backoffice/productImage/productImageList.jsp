<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
	<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>상품코드</th>
				<th>이미지</th>
				<th>이미지 설명</th>
				<th>삭제</th>
				<th>수정</th>				
			</tr>
			<c:forEach items="${list}" var="tmp">
			<tr>
			<td>${tmp.prdtCode}</td>
			<td><img src="${tmp.prdtImagePath}"/></td>
			<td>
			<c:choose>
			<c:when test="${tmp.prdtImageCode } eq '0'"><p>대표이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '1'"><p>목록이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '2'"><p>작은 목록이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '5'"><p>상세이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '6'"><p>상세이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '7'"><p>상세이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '8'"><p>상세이미지</p></c:when>
			<c:when test="${tmp.prdtImageCode } eq '9'"><p>상세이미지</p></c:when>
			</c:choose>
			</td>
			<td><a href="#">삭제</a></td>
			<td><a href="#">수정</a></td>
			</tr>
			</c:forEach>
		</table>

		<a href="/shop/admin/productRegister" class="btn btn-default">작성하기</a>
	</div>