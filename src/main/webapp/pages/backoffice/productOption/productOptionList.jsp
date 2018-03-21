<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>

 function goModify(prdtCode){
		
		document.location.href = "/admin/pomForm/"+prdtCode;
	}
 
 $(document).ready(function(){
	 
	 var set = $('.colorset2').val();
	
	/* $('.colorset').css('background-color', set); */
	
	 $('.colorset').each(function(idx, obj) {
		 $('.colorset').css('background-color', $(this).val());
		});
	
	 
	
 });
 

</script>

<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th>상품코드</th>
				<th>색상 코드</th>
				<th>생상 이름</th>
				<th>사이즈</th>
				<th colspan="3">재고</th>
				<th>상품등록일</th>			
				<th>생성자</th>		
				<th>수정/삭제</th>
						
			</tr>
			
			 <c:forEach items="${productOptionList}" var="option">
			 
				<tr>
				
					<td>${option.prdtCode }</td>
					
					<td> <button class="colorset" value="${option.prdtColorCode }" style="border:1px solid black; width:15px; height:15px"></button>
					<input class="colorset2" type="hidden" value="${option.prdtColorCode }">${option.prdtColorCode }</td>
					
					<td>${option.colorName }</td>
					<td>${option.prdtSize }</td> 
					<td>S:${option.prdtLaveCount }</td>
					<td>M</td>
					<td>L</td>
					<td>${option.regtDtm }<%-- <fmt:formatDate value="" pattern="yyyy-MM-dd HH:mm:ss"/> --%></td>
					<td>${option.rgsId }</td>	
					<td>
						
							<input type="hidden" name="modifydelete" value="${option.prdtCode }">
							<a href="javascript:goModify('${option.prdtCode}');"><button class="modify">수정</button></a>
							<button type="submit" class="delete">삭제</button> 
						
					</td>	
										    								      
				</tr>  
				
			</c:forEach> 
			
		</table>

		<a href="/admin/porForm" class="btn btn-default pull-right">옵션등록</a>

</div>		

                          
