<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
	<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>제목</th>
				<th>작성일</th>
				<th>삭제</th>
				<th>제목</th>
				<th>작성일</th>
				<th>삭제</th>				
			</tr>
			<tr>
				<td>
					<a href="/admin/products/detail/1">
					나이키신발
					</a>
				</td>
				<td>
					2017-12-05
				</td>
				<td>
					<a href="/admin/products/delete/1" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
				</td> 
				<td>
					<a href="/admin/products/detail/1">
					나이키신발
					</a>
				</td>
				<td>
					2017-12-05
				</td>
				<td>
					<a href="/admin/products/delete/1" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
				</td>    				      
			</tr>  
		</table>

		<a href="/admin/products/write" class="btn btn-default">작성하기</a>
	</div>