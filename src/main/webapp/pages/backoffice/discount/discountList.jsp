<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
$(function($) {
	
	$( "#dialog" ).dialog({
		title: "상품리스트 등록",
		autoOpen: false,
		width:600,
	  	buttons: [
	    	{
	      		text: "SAVE",
	      		click: function() {
	        		saveFile();
	      		}
	    	}
	  	]
	});
	
});

var selectSeq;

function saveFile(){
	// 파일 확장자 검사 추가하기 
	var form = new FormData(document.getElementById('uploadForm'));

	 $.ajax({
	     url: "/admin/discount/saveFile.ajax?dcntSeq=" + selectSeq,
	     data: form,
	     dataType: 'text', 
	     processData: false, 
	     contentType: false,
	     type: 'POST',
	     success: function(data){
	         if(data == 'success'){
	        	 alert('저장성공');
	        	 location.reload();
	         }else{
	        	 alert('저장실패');
	         }
	     }
	 })
}

function showDialog(dcntSeq, dcntName){
	console.log(dcntName);
	selectSeq = dcntSeq;
	$("#dcntName").html(dcntName);
	$( "#dialog" ).dialog( "open" );
}

function goRegister(){
	var encodingUrl = encodeURIComponent("${discount.pagegUrl}${discount.pageNo}");
	document.location.href = "/admin/discount/discountRegisterForm?listUrl="+encodingUrl;
}

function goModify(dcntSeq){
	var encodingUrl = encodeURIComponent("${discount.pagegUrl}${discount.pageNo}");
	document.location.href = "/admin/discount/discountModifyForm/"+dcntSeq+"?listUrl="+encodingUrl;
}

</script>
	<div class="col-sm-10">
		<table class="table table-bordered table-hover"> 
			<tr>
				<th class="text-center">NO</th>
				<th class="text-center">할인명</th>
				<th class="text-center">할인가격</th>
				<th class="text-center">할인타입</th>
				<th class="text-center">할인시작일</th>
				<th class="text-center">할인종료일</th>		
				<th class="text-center">등록자</th>		
				<th class="text-center">등록일</th>			
				<th class="text-center">상품등록</th>		
				<!-- 상품할인 삭제 추가하기 -->	
				<!-- <th class="text-center">삭제</th> -->
			</tr>
			<c:forEach items="${discountList}" var="discount" varStatus="status">
				<tr>
					<td class="text-center">
						${status.count}
					</td>
					<td>
						<a href="javascript:goModify('${discount.dcntSeq}');">
							${discount.dcntName}
						</a>
					</td>
					<td>
						<fmt:formatNumber value="${discount.dcntPrice}" pattern="#,###" />
					</td> 
					<td>
						${discount.dcntType}
					</td>
					<td class="text-center">
						<fmt:formatDate value="${discount.dcntStartDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td class="text-center">
						<fmt:formatDate value="${discount.dcntEndDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${discount.dcntRegId}
					</td>										    								      
					<td class="text-center">
						<fmt:formatDate value="${discount.dcntRegDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>										    								      
					<td class="text-center">
						<a href="javascript:;" class="btn btn-default btn-xs" id="fileUploadBtn" onclick="showDialog('${discount.dcntSeq}','${discount.dcntName}')">등록</a>
					</td>										    								      
				</tr>  
			</c:forEach>
		</table>

		<a href="javascript:goRegister();" class="btn btn-default pull-right">작성하기</a>

         <c:import url="/admin/common/paging" charEncoding="utf-8">
             <c:param name="startPageNo" value="${discount.startPageNo}" />
             <c:param name="endPageNo" value="${discount.endPageNo}" />
             <c:param name="firstPageNo" value="${discount.firstPageNo}" />
             <c:param name="nextPageNo" value="${discount.nextPageNo}" />
             <c:param name="pageNo" value="${discount.pageNo}" />
             <c:param name="pagegUrl" value="${discount.pagegUrl}" />
         </c:import>	
         
	</div>
	
	<!-- 템플릿 다운로드 추가 -->
	<!-- 파일 등록 모달 -->
	<div id="dialog" style="display:none;">
		할인명 : <span id="dcntName"></span><hr>
		<form id="uploadForm" enctype="multipart/form-data"> 
			<input type="file" id="fileId" name="file-data"/> 
		</form> 
	</div>
	
	<!-- 등록된 상품 리스트 확인 모달 추가하기 -->
	<!-- 모달 내 등록된 상품 삭제 기능 추가 -->