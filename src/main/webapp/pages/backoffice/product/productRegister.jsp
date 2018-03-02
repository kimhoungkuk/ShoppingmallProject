<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript" src="/shop/js/common.js"></script>
<script type="text/javascript">
	$(function($) {
      $('#summernote').summernote({
	          placeholder: 'Hello bootstrap 4',
	          tabsize: 1,
	          height: 300,
	          lang: 'ko-KR' // default: 'en-US'
        });
		 
	    $('.btn-primary').click(function() {
	    	frmSubmit();
	    });
	}); 

	function frmSubmit(){
        // 교환내용
        if(isEmpty($("#prdtKorName").val())) {
            alert("상품한글명을 입력해주세요.");
            $("#prdtKorName").focus();
            return;
        }
        
	}
</script>
		<div class="col-sm-10">
			<form  method="post"  id="prdtForm" name="prdtForm">
				<table class="table table-bordered">
					<tr>
						<th>상품한글명</th>
						<td>
							<input type="text" name="prdtKorName" id="prdtKorName" class="form-control" />
						</td>
					</tr>
					<tr>
						<th>상품영문명</th>
						<td>
							<input type="text" name="prdtEngName" id="prdtEngName" class="form-control"  />
						</td>
					</tr>
					<tr>
						<th>상품판매가</th>
						<td>
							<input type="text" name="prdtSellPrice" id="prdtSellPrice" class="form-control"  />
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
						<th>상품정보</th>
						<td>
							 <div id="summernote"></div>
						</td>
					</tr>																		
				</table>
				<button type="button" class="btn btn-primary">작성하기</button>
			</form>
		</div>