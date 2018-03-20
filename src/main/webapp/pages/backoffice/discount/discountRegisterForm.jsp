<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
	$(function($) {
		/* $("input[name='datepicker']").datepicker({
            dateFormat: "yyyy-mm-dd",
        	calendarWeeks: false,
            todayHighlight: true,
            autoclose: true,
            language: "kr"
	    });
	    $("input[name='datepicker']").datepicker('setDate', new Date()); */

	    $('#register').click(function() {
	    	frmSubmit();
	    });
	});
	
	function frmSubmit(){
		
        /* validation check
        if(isEmpty($("#prdtKorName").val())) {
            alert("상품한글명을 입력해주세요.");
            $("#prdtKorName").focus();
            return;
        }
        */
		console.log(document.dcntForm)
        document.dcntForm.submit();
        
	}
</script>
<div class="col-sm-10">
	<div class="page-header" style="margin-top:6px">
	  <h4>상품할인 등록</h1>
	</div>
	<form class="form-horizontal" action="/admin/discount/discountRegister" method="post" id="dcntForm" name="dcntForm">
		<div class="form-group">
			<label for="dcntName" class="col-sm-2 control-label">할인명</label>
			<div class="col-sm-10">
				<input type="text" name="dcntName" id="dcntName" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="datepicker" class="col-sm-2 control-label">기간설정</label>
			<div class="col-sm-10">
				<input name="datepicker" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="dcntPrice" class="col-sm-2 control-label">할인가격</label>
			<div class="col-sm-10 form-inline">
				<input type="text" name="dcntPrice" id="dcntPrice" class="form-control" />
				<select class="form-control" name="dcntType" id="dcntType">
				  <option value="1">%</option>
				  <option value="2">-</option>
				</select>
			 </div>
		</div>
		<div class="form-group">
			<label for="dcntPrice" class="col-sm-2 control-label">상품선택</label>
			<div class="col-sm-10 ">
				<label class="radio-inline">
				  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 미등록
				</label>
				<label class="radio-inline">
				  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 전체상품
				</label>
				<label class="radio-inline">
				  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> 특정상품
				</label>
				<label class="radio-inline">
				  <input type="radio" name="inlineRadioOptions" id="inlineRadio4" value="option4"> 제외상품
				</label>
				<label class="radio-inline">
				  <input type="radio" name="inlineRadioOptions" id="inlineRadio5" value="option5"> 카테고리
				</label>
			 </div>
		</div>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" id="list">목록</button>
			<button type="button" class="btn btn-primary" id="register">작성하기</button>
		</div>
	</form>
</div>
