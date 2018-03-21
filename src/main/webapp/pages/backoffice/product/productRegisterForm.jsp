<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.fileDiv .box {
	display: table;
	width: 100px;
	height: 100px;
	background-color: #f0f0f0;
	margin: 10px auto;
}

.fileDiv .glyphicon-camera {
	display: table-cell;
	text-align: center;
	vertical-align: middle;
	font-size: 30pt;
}

.fileDiv td p {
	font-size: 10pt;
	margin: 0px;
}

.fileDiv td footer {
	font-size: 5pt;
}

.fileDiv td {
	text-align: center;
}

.fileDiv th {
	text-align: center;
}

.fileDiv .btn-file {
	position: relative;
	overflow: hidden;
}

.fileDiv .btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	cursor: inherit;
	display: block;
}

ul{
    list-style:none;
    padding-left:0px;
}

</style>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
	var categories = undefined;

	<c:if test="${not empty categoryList}">
		categories = new Array();
		<c:forEach items="${categoryList }" var="category">
		categories.push({'id': '${category.ctgrId }'
											 , 'parent': ('${category.prntCtgrId }' == '') ? '#' : '${category.prntCtgrId }'
											 , 'text': '${category.ctgrName}'
											});
		</c:forEach>
	</c:if>

	$(function($) {  
		
	  var categoryHtml = "<option value=\"\">1Depth 선택 </option>";
		  
	  for(var i=0;i < categories.length;i++){
		  if(categories[i].parent == '#')
		  {
			  categoryHtml += "<option value=\""+categories[i].id+"\">"+categories[i].text+"</option>";
		  }
	  }

	  $("#category1").html(categoryHtml);
	  
      $('.summernote').summernote({
	          placeholder: '상품정보 입력',
   	  		  minHeight:100,
			  maxHeight:null,	          
	          tabsize: 1,
	          height: 600,
	          lang: 'ko-KR' // default: 'en-US'
        });
		 
		//대표이미지 선택(단일 파일 선택)
		$("#input_img1").on("change", handleImgFileSelect);
		$("#input_img1").on("change", function() {
			num = 1
		});

		$("#input_img2").on("change", handleImgFileSelect);
		$("#input_img2").on("change", function() {
			num = 2
		});

		$("#input_img3").on("change", handleImgFileSelect);
		$("#input_img3").on("change", function() {
			num = 3
		});

		//상세이미지 선택했을때(multiFile)
		$("#input_imgs").on("change", handleImgFilesSelect);

	    $('#register').click(function() {
	    	frmSubmit();
	    });
	    
	    $('#list').click(function() {
	    	var decodingUrl = decodeURIComponent("${param.listUrl}");
	    	document.location.href="${param.listUrl}";
	    });
	    

	   //상품 카테고리 추가
	   $("#btnAdd").on("click", function(){ 
		     var target1 = document.getElementById("category1");
		     var categoryText1 = target1.options[target1.selectedIndex].text;
		     
		     var target2 = document.getElementById("category2");
		     var categoryText2 = target2.options[target2.selectedIndex].text;
		     
		     var target3 = document.getElementById("category3");
		     var categoryText3 = target3.options[target3.selectedIndex].text;
		     var categoryValue3 = target3.options[target3.selectedIndex].value;

		   	 $("#categoryList").append("<li>"+categoryText1+" > "+categoryText2+" > "+categoryText3+" <a href=\"#\" id=\"btnDelete\">삭제</a><input type=\"hidden\" name=\"ctgrId\" id=\"ctgrId\" value=\""+categoryValue3+"\"></li>"); 
   		   	 categoryDelete();
		   	 return false; 
		});
	    
	}); 
	
	function categoryDelete(){
		   //상품 카테고리 삭제
		   $("#categoryList>li>#btnDelete").on("click", function(){ 
			   	$(this).parent().remove(); 
			   	return false; 
		   }); 		   
	}
	
	function frmSubmit(){
		
        // 교환내용
        if(isEmpty($("#prdtKorName").val())) {
            alert("상품한글명을 입력해주세요.");
            $("#prdtKorName").focus();
            return;
        }
        
		//사진 세장 모두 등록되어있으면
		if ($(".imgs").size() < 3) {
			alert("상품 대표 이미지를 모두 등록해 주세요");
			return;
		}
		
        document.prdtForm.submit();
        
	}
	
	//사진 미리보기
	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("이미지만 가능합니다.");
				return;
			}

			sel_file = f;
			var reader = new FileReader();
			console.log(1);
			reader.onload = function(e) {
				console.log(2);
				//미리보기 이미지 지우고 등록한 이미지 추가
				$("#img_wrap" + num).empty().append("<img id='img"+num+"' class='imgs' height='100px' width='100px' src="+e.target.result+">");
				console.log(3);
			
			}
			console.log(4);
			reader.readAsDataURL(f);
			console.log(5);
			
		});

	}

	function handleImgFilesSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		if (filesArr.length<=5 && filesArr.length>0) {
			sel_files = [];
			num = 5;
			$(".box2").empty().append("<i class='glyphicon glyphicon-camera'></i>");
			filesArr.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("이미지만 가능합니다.");
					return;

				}
				sel_files.push(f);
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#img_wrap" + num).empty().append("<img height='100px' width='100px' src='"+e.target.result+"'"+"+/><button type='button' onclick='button_click("+ num+ ");' class='close' id='close"+ num+ "' style='position:absolute;'><span aria-hidden='true'>&times;</span></button>");
					num++;
				}
				reader.readAsDataURL(f);
			});

		} else if (filesArr.length > 5) {
			alert("상세사진은 5장 까지만 첨부 가능합니다.");
		}
	}
	
	function goCategory(obj , ctgrNum){
		 
		
		  var prntCtgrId = obj.value;
	      var categoryHtml = "<option value=\"\">"+ctgrNum+"Depth 선택 </option>";
			  
		  for(var i=0;i < categories.length;i++){
			  if(categories[i].parent == prntCtgrId)
			  {
				  categoryHtml += "<option value=\""+categories[i].id+"\">"+categories[i].text+"</option>";
			  }
		  }

		  $("#category"+ctgrNum).html(categoryHtml);
		  
	}
	
</script>
		<div class="col-sm-10">
			<form action="/admin/product/productRegister" enctype="multipart/form-data" method="post"  id="prdtForm" name="prdtForm">
				<table class="table table-bordered">		
			        <colgroup>
			            <col width="15%" />
			            <col width="85%" />  
			        </colgroup>
			        <tbody>				
					<tr>
						<th class="col-md-2">상품한글명</th> 
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
						<th rowspan="2">상품카테고리</th>
						<td class="input-group triple-input">
							    <select id="category1" name="category1" class="form-control" style="width:30%;" onChange="javascript:goCategory(this,'2');">
							        <option value="Y">전시</option>
							        <option value="N">미전시</option>
							    </select> 			
							    <select id="category2" name="category2" class="form-control" style="width:30%;margin-left:5px" onChange="javascript:goCategory(this,'3');">
							    </select>		
							    <select id="category3" name="category3" class="form-control" style="width:30%;margin-left:5px">
							    </select>	 
							    <a href="#" id="btnAdd" style="margin-left:5px">추가</a>				    	    						    
						</td>
					</tr>		
					<tr> 
						<td>
							<ul id="categoryList">
							</ul> 						    
						</td>
					</tr>											
					<tr>
						<th>상품상세정보</th>
						<td>
							<textarea name="prdtDetail" class="summernote" id="prdtDetail" ></textarea>
						</td>
					</tr>		
					</tbody>																					
				</table>
				<table class="table table-board container fileDiv">
					<thead>
						<tr>
							<th colspan='3'>상품 대표 이미지 등록</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
							<div class='box' id='img_wrap1'>
									<i class="glyphicon glyphicon-camera"></i>
							</div>
							</td>
							<td><div class='box' id='img_wrap2'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
		
							<td><div class='box' id='img_wrap3'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
						</tr>
						<tr>
							<td><p>대표 이미지</p></td>
							<td><p>목록 이미지</p></td>
							<td><p>작은 목록 이미지</p></td>
						</tr>
						<tr>
							<td><footer>권장 500px*500px</footer></td>
							<td><footer>권장 300px*300px</footer></td>
							<td><footer>권장 100px*100px</footer></td>
						</tr>
		
						<input type="hidden" name="prdtCode" value="00">
						<!--상품 코드 넘겨주기-->
						<!--	<input type="hidden" name="prdtImageCode" value="11">상품 이미지 코드 넘겨주기-->
						<tr>
							<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
									<input name="input_img" type="file" id="input_img1">
							</span></td>
							<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
									<input name="input_img" type="file" id="input_img2">
							</span></td>
							<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
									<input name="input_img" type="file" id="input_img3">
							</span></td>
						</tr>
					</tbody>
				</table>
				<table class="table table-board container fileDiv">
					<thead>
						<tr>
							<th colspan='5'>상품 상세 이미지 등록(최대 5장까지 등록 가능합니다.)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><div class='box box2' id='img_wrap5'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
							<td><div class='box box2' id='img_wrap6'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
							<td><div class='box box2' id='img_wrap7'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
							<td><div class='box box2' id='img_wrap8'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
							<td><div class='box box2' id='img_wrap9'>
									<i class="glyphicon glyphicon-camera"></i>
								</div></td>
						</tr>
						<tr>
							<td colspan='5'><footer>권장 500px*500px</footer></td>
						</tr>
						<tr>
							<td colspan='5'><span class="btn btn-primary btn-xs btn-file">
									사진등록 
								<input name="input_imgs" type="file" id="input_imgs" multiple />
							</span></td>
						</tr>					
					</tbody>
					<tfoot>
						<tr>
							<td colspan='5'>
								<button type="button" class="btn btn-primary" id="list">목록</button>
								<button type="button" class="btn btn-primary" id="register">작성하기</button>
							</td>
						</tr>
					</tfoot>
				</table>			
			</form>
		</div>
