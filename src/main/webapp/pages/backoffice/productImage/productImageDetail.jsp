<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
	//필수이미지 선택할때 사용할 변수
	var sel_file;
	//상세 이미지 선택할때 사용할 배열
	var sel_files;
	var num;

	$(document).ready(function() {
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


	});


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
	
	function goDelete(){
		document.delfrm.submit();
	}
</script>


<div class="col-sm-10 container fileDiv">
	<form:form action="/admin/productImage/${list[0].prdtCode}" id="delfrm" name="delfrm" method="DELETE">
		<button type="button" class="btn btn-default" onClick="javascript:goDelete();">상품코드 ${list[0].prdtCode}번 이미지 삭제</button>
	</form:form>
	<form:form action="/admin/productImage/update/${list[0].prdtCode}" enctype="multipart/form-data" id="imgform" method="POST">
		<table class="table table-board">
			<thead>
				<tr>
					<th colspan='3'>상품 대표 이미지 수정</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
					<div class='box' id='img_wrap1'>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[0].prdtSaveImageName}"/>
					</div>
					</td>
					<td>
						<div class='box' id='img_wrap2'>
							<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[1].prdtSaveImageName}"/>
						</div>
					</td>

					<td><div class='box' id='img_wrap3'>
							<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[2].prdtSaveImageName}"/>
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
				<!--상품 코드&이미지코드 넘겨주기-->
				<input type="hidden" name="prdtCode" value="${list[0].prdtCode}"/>
				<input type="hidden" name="prdtImageCode" value="${list[0].prdtImageCode}"/>
				<tr>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진수정
							<input name="input_img1" type="file" id="input_img1"/>
					</span></td>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진수정
							<input name="input_img2" type="file" id="input_img2"/>
					</span></td>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진수정
							<input name="input_img3" type="file" id="input_img3"/>
					</span></td>
				</tr>
			</tbody>
		</table>
		<table class="table table-board">
			<thead>
				<tr>
					<th colspan='5'>상품 상세 이미지 등록(최대 5장까지 등록 가능합니다.)</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><div class='box box2' id='img_wrap5'>
					<c:choose>
					<c:when test="${empty list[3].prdtSaveImageName}">
						<i class="glyphicon glyphicon-camera"></i>					
					</c:when>
					<c:otherwise>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[3].prdtSaveImageName}"/>
					</c:otherwise>
					</c:choose>
						</div></td>
					<td><div class='box box2' id='img_wrap6'>
					<c:choose>
					<c:when test="${empty list[4].prdtSaveImageName}">
						<i class="glyphicon glyphicon-camera"></i>					
					</c:when>
					<c:otherwise>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[4].prdtSaveImageName}"/>
					</c:otherwise>
					</c:choose>

						</div></td>
					<td><div class='box box2' id='img_wrap7'>
					<c:choose>
					<c:when test="${empty list[5].prdtSaveImageName}">
						<i class="glyphicon glyphicon-camera"></i>					
					</c:when>
					<c:otherwise>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[5].prdtSaveImageName}"/>
					</c:otherwise>
					</c:choose>
						</div></td>
					<td><div class='box box2' id='img_wrap8'>
					<c:choose>
					<c:when test="${empty list[6].prdtSaveImageName}">
						<i class="glyphicon glyphicon-camera"></i>					
					</c:when>
					<c:otherwise>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[6].prdtSaveImageName}"/>
					</c:otherwise>
					</c:choose>
						</div></td>
					<td><div class='box box2' id='img_wrap9'>
					<c:choose>
					<c:when test="${empty list[7].prdtSaveImageName}">
						<i class="glyphicon glyphicon-camera"></i>					
					</c:when>
					<c:otherwise>
						<img height='100px' width='100px' src="${pageContext.request.contextPath}/upload/${list[7].prdtSaveImageName}"/>
					</c:otherwise>
					</c:choose>

						</div></td>
				</tr>
				<tr>
					<td colspan='5'><footer>권장 500px*500px</footer></td>
				</tr>
				<tr>
					<td colspan='5'><span class="btn btn-primary btn-xs btn-file">
							사진 수정 
						<input name="input_imgs" type="file" id="input_imgs" multiple />
					</span></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan='5'><input type="submit" class="btn btn-default"
						id="sendBtn" value="전송" /></td>
				</tr>
			</tfoot>
		</table>
	</form:form>
</div>