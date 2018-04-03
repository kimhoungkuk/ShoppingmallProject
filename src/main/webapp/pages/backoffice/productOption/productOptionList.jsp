<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

 function goModify(prdtCode){
		
		document.location.href = "/admin/pomForm/"+prdtCode;
	}
 
 $(document).ready(function(){
	 
	 $('.colorset').each(function(idx, obj) {
		 $(this).css('background-color', $(this).val());
		});

	
 });
 

</script>

<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>상품코드</th>
				<th>색상 코드</th>
				<th>색상 이름</th>
				<th>사이즈</th>
				<th>재고</th>
				<th>상품등록일</th>			
				<th>생성자</th>		
				<th>수정/삭제</th>
						
			</tr>
			
			 <c:forEach items="${productOptionList}" var="option">
			 
				<tr>
					
					<td class="prdtcode">${option.prdtCode }</td>
					
					<td> <button class="colorset" value="${option.prdtColorCode }" style="border:1px solid black; width:15px; height:15px"></button>
					${option.prdtColorCode }</td>
					<td>${option.colorName }</td>
					<td>${option.prdtSize }</td> 
					<td>${option.prdtLaveCount } 개</td>
					<td>${option.regtDtm }</td>
					<td>${option.rgsId }</td>	
					<td>
						
							
							<a href="javascript:goModify('${option.prdtCode}');"><button class="modify">수정/삭제</button></a>
							
						
					</td>	
									    								      
				</tr>  
			
			</c:forEach> 
			
		</table>

		<a href="/admin/porForm" class="btn btn-default pull-right">옵션등록</a>

</div>