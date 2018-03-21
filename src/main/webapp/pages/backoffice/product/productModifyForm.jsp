<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
	$(function($) {
	  var prdtDispYn = '${product.prdtDispYn}'; 
	  $("#prdtDispYn").val(prdtDispYn);
	  
      $('.summernote').summernote({
	          placeholder: '상품정보 입력',
	          tabsize: 1,
	          height: 300,
	          lang: 'ko-KR' // default: 'en-US'
        });
		 
	    $('#register').click(function() {
	    	frmSubmit();
	    });
	    
	    $('#list').click(function() {
	    	var decodingUrl = decodeURIComponent("${param.listUrl}");
	    	document.location.href="${param.listUrl}";
	    });
	    
	}); 

	function frmSubmit(){
		
        // 교환내용
        if(isEmpty($("#prdtKorName").val())) {
            alert("상품한글명을 입력해주세요.");
            $("#prdtKorName").focus();
            return;
        }
        
        document.prdtForm.submit();
        
	}
</script>
		<div class="col-sm-10">
			<form action="/admin/product/productModify" method="post"  id="prdtForm" name="prdtForm">
				<input type="hidden" id="prdtCode" name="prdtCode" value="${product.prdtCode}">
				<table class="table table-bordered">
					<tr>
						<th>상품코드</th> 
						<td>
							${product.prdtCode}
						</td>
					</tr>				
					<tr>
						<th>상품한글명</th> 
						<td>
							<input type="text" name="prdtKorName" id="prdtKorName" value="${product.prdtKorName}" class="form-control" />
						</td>
					</tr>
					<tr>
						<th>상품영문명</th>
						<td>
							<input type="text" name="prdtEngName" id="prdtEngName" value="${product.prdtEngName}" class="form-control"  />
						</td>
					</tr>
					<tr>
						<th>상품판매가</th>
						<td>
							<input type="text" name="prdtSellPrice" id="prdtSellPrice" value="${product.prdtSellPrice}" class="form-control"  />
						</td>
					</tr>
					<tr>
						<th>상품전시여부</th>
						<td>
						    <select name="prdtDispYn" id="prdtDispYn" class="form-control"  >
						        <option value="Y">전시</option>
						        <option value="N">미전시</option>
						    </select>
						</td>
					</tr>
					<tr>
						<th>상품상세정보</th>
						<td>
							<textarea name="prdtDetail" class="summernote" id="prdtDetail">${product.prdtDetail}</textarea>
						</td>
					</tr>																		
				</table>
				<button type="button" class="btn btn-primary" id="list">목록</button>
				<button type="button" class="btn btn-primary" id="register">작성하기</button>
			</form>
		</div>