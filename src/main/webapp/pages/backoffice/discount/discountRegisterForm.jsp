<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
	var productList = [];
	
	$(function($) {
		//getProductList();
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
	    $("input[name='dcntStartDateStr']").datepicker('setDate', new Date()); 
	    $("input[name='dcntEndDateStr']").datepicker('setDate', new Date()); 
	    
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
	    
	    $("input[name='inlineRadioOptions']").change(function(){
	        if ($("#inlineRadio1").is(':checked')) {
				console.log('inlineRadio1');
	        } else if($("#inlineRadio2").is(':checked')) {
	        	console.log('inlineRadio2');
			}
	    });
	});
	
	/*
	* form submit
	*/
	function frmSubmit(){
		
        // validation check
        if(isEmpty($("#dcntName").val())) {
            alert("상품할인명을 입력해주세요.");
            $("#dcntName").focus();
            return;
        }
        
        if(isEmpty($("#dcntStartDateStr").val())) {
            alert("할인 시작일을 입력해주세요.");
            $("#dcntStartDateStr").focus();
            return;
        }
        
        if(isEmpty($("#dcntEndDateStr").val())) {
            alert("할인 종료일을 입력해주세요.");
            $("#dcntEndDateStr").focus();
            return;
        }
        
        if(isEmpty($("#dcntPrice").val())) {
            alert("할인가를 입력해주세요.");
            $("#dcntPrice").focus();
            return;
        }
        
        if(!$.isNumeric($("#dcntPrice").val())) {
            alert("할인가를 숫자로 입력해주세요.");
            $("#dcntPrice").focus();
            return;
        }
        
        // 가격 타입에 따른 validation
        if($("#dcntType").val() == 1){
        	
        }
        
        
        /* var formData = $("#dcntForm").serialize();
        formData.productList = [];
        console.log(formData);
        $.ajax({
            type: "POST",
            url: "/admin/discount/discountRegister",
            dataType: 'JSON',
            data: formData,
            //contentType: 'application/json;charset=utf-8',
            success: function(json) {
                // 받아온 데이터 파싱 후 
                var json_data = JSON.stringify(json);
                var parse_data = JSON.parse(json_data);
         
            },
            error: function() {
            }        
        }); */

        document.dcntForm.submit();
        
	}
	
    /*
    * 상품목록 ajax
    */
    /* function getProductList(){
    	var me = this;
    	$.ajax({
	   	    url: '/admin/product/dcntProductList',
	   	 	type: 'GET',
			dataType: 'JSON',
			async: false,
			success: function(payload) {
				console.log(payload);
				productList = payload;
			}, error: function(xhr, status, text) {
				console.log('return false');
				alert(status);
			}
		});
    } */
</script>
<div class="col-sm-10">
	<div class="page-header" style="margin-top:6px">
	  <h4 onclick="console.log(productList)">상품할인 등록</h1>
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
			<div class="col-sm-10 form-inline">
				<input class="form-control" name="dcntStartDateStr" id="dcntStartDateStr"/> ~
				<input class="form-control" name="dcntEndDateStr" id="dcntEndDateStr"/>
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
		<div class="pull-right">
			<button type="button" class="btn btn-primary" id="list">목록</button>
			<button type="button" class="btn btn-primary" id="register">작성하기</button>
		</div>
	</form>
</div>
