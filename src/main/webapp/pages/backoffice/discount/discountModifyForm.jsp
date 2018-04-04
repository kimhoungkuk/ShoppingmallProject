<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
	$(function($) {
		/*
		* datepicker 설정
		*/
		var dpOption = {
				dateFormat: "yyyy-mm-dd",
	        	calendarWeeks: false,
	            todayHighlight: true,
	            autoclose: true,
	            language: "kr",
	            closeText: '닫기',
	            prevText: '이전',
	            nextText: '다음',
	            currentText: '오늘',
	            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	            dayNames: ['일','월','화','수','목','금','토'],
	            dayNamesShort: ['일','월','화','수','목','금','토'],
	            dayNamesMin: ['일','월','화','수','목','금','토'],
	            weekHeader: 'Wk',
	            dateFormat: 'yy-mm-dd',
	            firstDay: 0,
	            isRTL: false,
	            showMonthAfterYear: true,
	            yearSuffix: ''
		}
		$("input[name='dcntStartDateStr']").datepicker(dpOption);
		$("input[name='dcntEndDateStr']").datepicker(dpOption);
	    $("input[name='dcntStartDateStr']").datepicker('setDate', new Date('${discount.dcntStartDate}')); 
	    $("input[name='dcntEndDateStr']").datepicker('setDate', new Date('${discount.dcntEndDate}')); 
	    
	    /*
	    * form submit
	    */
	    $('#register').click(function() {
	    	frmSubmit();
	    });
	    
	    $('#list').click(function() {
	    	var decodingUrl = decodeURIComponent("${param.listUrl}");
	    	document.location.href="${param.listUrl}";
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
        document.dcntForm.submit();
        
	}
</script>
<div class="col-sm-10">
	<div class="page-header" style="margin-top:6px">
	  	<h4>상품할인 등록</h4>
	</div>
	<form class="form-horizontal" action="/admin/discount/discountModify" method="post" id="dcntForm" name="dcntForm">
		<input type="hidden" id="dcntSeq" name="dcntSeq" value="${discount.dcntSeq}">
		<div class="form-group">
			<label for="dcntName" class="col-sm-2 control-label">할인명</label>
			<div class="col-sm-10">
				<input type="text" name="dcntName" id="dcntName" value="${discount.dcntName}" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="datepicker" class="col-sm-2 control-label">기간설정</label>
			<div class="col-sm-10 form-inline">
				<input class="form-control" name="dcntStartDateStr" id="dcntStartDateStr"/> ~
				<input class="form-control" name="dcntEndDateStr" id="dcntEndDateStr"/>
			</div>
		</div>
		<div class="form-group">
			<label for="dcntPrice" class="col-sm-2 control-label">할인가격</label>
			<div class="col-sm-10 form-inline">
				<input type="text" name="dcntPrice" id="dcntPrice" value="${discount.dcntPrice}" class="form-control" />
				<select class="form-control" name="dcntType" id="dcntType" value="${discount.dcntType}">
				  <option value="1">%</option>
				  <option value="2">-</option>
				</select>
			 </div>
		</div>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" id="list">목록</button>
			<button type="button" class="btn btn-primary" id="register">작성하기</button>
		</div>
	</form>
</div>