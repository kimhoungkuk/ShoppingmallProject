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
	
	$( "#productDialog" ).dialog({
		title: "상품리스트",
		autoOpen: false,
		width:600
	});
	
});

var selectSeq;
var discount = {};
var productList = [];

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
	     },
	     error:function(request,status,error){
	    	 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	     }
	 })
}

function showDialog(){
	//selectSeq = dcntSeq;
	//$("#dcntName").html(dcntName);
	$( "#dialog" ).dialog( "open" );
}

function showListDialog(dcntSeq, dcntName){
	selectSeq = dcntSeq;
	$("#dcntName").html(dcntName);
	getProductList();
	$( "#productDialog" ).dialog( "open" );
}

function goRegister(){
	var encodingUrl = encodeURIComponent("${discount.pagegUrl}${discount.pageNo}");
	document.location.href = "/admin/discount/discountRegisterForm?listUrl="+encodingUrl;
}

function goModify(dcntSeq){
	var encodingUrl = encodeURIComponent("${discount.pagegUrl}${discount.pageNo}");
	document.location.href = "/admin/discount/discountModifyForm/"+dcntSeq+"?listUrl="+encodingUrl;
}

function getProductList(){
	$.ajax({
	     url: "/admin/discount/getProductList.ajax?dcntSeq=" + selectSeq,
	     type: 'GET',
	     success: function(data){
	    	 productList = data;
	         console.log(data);
	         var div = document.querySelector('#productDialog tbody');
	         var html = '';
	         for (var i = 0; i < data.length; i++) {
	             html += '<tr><td>' + data[i].prdtCode
	                     + '</td><td> ' + data[i].prdtKorName 
	                     + '</td><td> ' + data[i].prdtSellPrice 
	                     + '</td><td><a href="javascript:;" class="btn btn-default btn-danger" onclick="deletePrdtDcnt(' + i + ')">'
	                     + '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> ';
	                     + '</td></tr>';
	         }
	         console.log(html);
	         div.innerHTML = html;
	     },
	     error:function(request,status,error){
	    	 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	     }
	 })
}

function deletePrdtDcnt(i){
	var obj = {};
	obj.prdtCode = productList[i].prdtCode;
	obj.dcntSeq = selectSeq;
	$.ajax({
	     url: '/admin/discount/deletePrdtDcnt.ajax',
	     type: 'POST',
	     dataType: 'json',
	     data: JSON.stringify(obj),
	     contentType: 'application/json;charset=utf-8',
	     success: function(data){
	    	 if(data){
		         alert('삭제 완료');
		         getProductList();
	    	 }else{
	    		 alert('삭제 실패');
	    	 }
	     },
	     error:function(request,status,error){
	    	 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	     }
	 })
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
				<th class="text-center">상품목록</th>		
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
						<a href="javascript:;" class="btn btn-default" onclick="showListDialog('${discount.dcntSeq}','${discount.dcntName}')">
							<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
						</a>
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
	
	<!-- 페이징 추가 -->
	<div id="productDialog" style="display:none;">
		<a href="javascript:;" class="btn btn-default" id="fileUploadBtn" onclick="showDialog()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 상품등록
		</a>
		<table class="table table-bordered table-hover" style="margin-top:20px"> 
			<thead>
				<tr>
					<th>상품코드</th>
					<th>상품한글명</th>
					<th>상품가격</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>