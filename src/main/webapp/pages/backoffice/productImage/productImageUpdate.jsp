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

		//사진 전송
		$("#sendBtn").click(function() {
			//사진 세장 모두 등록되어있으면
			if ($(".imgs").size() == 3) {
				$("#imgform").submit();
			} else {//사진이 모두 등록되어 있지 않다면
				alert("상품 대표 이미지를 모두 등록해 주세요");
			}
		});
	});

	/*

	 //삭제 기능 보류
	 function button_click(num){
	 	$("#img_wrap"+num).closest("tr").append("<td><div class='box box2' id='img_wrap9'><i class='glyphicon glyphicon-camera'></i></div></td>")
	 	$("#img_wrap"+num).closest("td").remove();
		var eventDiv = this.closest("div");
		$("#img_wrap"+num).empty().append("<i class='glyphicon glyphicon-camera'></i>");
	 }

	 */

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
</script>


<div class="col-sm-10 container fileDiv">
	<form action="productImage" enctype="multipart/form-data" id="imgform" method="POST">
		<table class="table table-board">
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

				<input type="hidden" name="prdtCode" value="01"/>
				<!--상품 코드 넘겨주기-->
				<!--	<input type="hidden" name="prdtImageCode" value="11">상품 이미지 코드 넘겨주기-->
				<tr>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
							<input name="input_img" type="file" id="input_img1"/>
					</span></td>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
							<input name="input_img" type="file" id="input_img2"/>
					</span></td>
					<td><span class="btn btn-primary btn-xs btn-file"> 사진등록
							<input name="input_img" type="file" id="input_img3"/>
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
					<td colspan='5'><input type="button" class="btn btn-default"
						id="sendBtn" value="전송" /></td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>