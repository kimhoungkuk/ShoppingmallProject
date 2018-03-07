<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/component/jstree/themes/default/style.min.css" />">
<script src="<c:url value="/resources/component/jstree/jquery.min.js" />"></script>
<script src="<c:url value="/resources/component/jstree/jstree.min.js" />"></script>
    
<script type="text/javascript">

	var $tree;
	var idx = 1;
	var categories = undefined;

	$(function() {
		<c:if test="${not empty categoryList}">
			categories = new Array();
			<c:forEach items="${categoryList }" var="category">
			categories.push({'id': '${category.ctgrId }'
												 , 'parent': ('${category.prntCtgrId }' == '') ? '#' : '${category.prntCtgrId }'
												 , 'text': '${category.ctgrName}'
												});
			</c:forEach>
		</c:if>
		
		// initialization of jstree when init data is empty
		// categories = undefined;
		
		// init function call
		init_jstree();
	});
	
	// jstree init
	function init_jstree() {
		var $target = $("#categoryTree");
		$target.jstree({
			
			// jsTree 에 활성 가능한 플러그인 명시.
			"plugins" : [
				// Drag&Drop
				"dnd"
			],

			"core": {
				
				/*
					jsTree 에 대한 어떠한 이벤트 발생 시 check_callback 을 우선적으로 먼저 타기 때문에
				 	그렇기에 수정, 삭제, 이동 등에 대한 validation 체크는 이곳에서 체크해야 한다.
				 	return true :: 해당 이벤트 적용. operation 에 bind 되어진 함수 호출
				 	return false :: 해당 이벤트 미적용. operation 에 bind 되어진 함수 호출 하지 않는다.
				*/
				"check_callback": function(operation, node, parent, position, more) {
					console.log('callback', operation);
					
					// drag & drop
					if (operation == 'move_node') {
						// parent 가 변경 된다면 데이터 및 구조가 깨지므로 같은 카테고리에서의 순서만 변경 가능
						if (node.parent != parent.id) {
							return false;
						} else {
							return true;
						}
						
					} else if (operation == 'create_node') {
						// 노드 생성 시
						$.ajax({
							url: '/shop/admin/category/register'
							, type: 'POST'
							, dataType: 'JSON'
							, data: {
								ctgrName: node.text
								, prntCtgrId: (parent.id == '#') ? '' : parent.id
							}, success: function(payload) {
								if (payload && payload.resultYn == 'Y' && payload.ctgrId != '' && payload.ctgrName != '') {
									alert('카테고리가 생성되었습니다.');
									node.id = payload.ctgrId;
									return true;
								} else {
									alert('카테고리 생성 중 오류가 발생하였습니다.');
									return false;
								}
							}, error: function(xhr, status, text) {
								alert(status);
								return false;
							}
						});
						
					} else if (operation == 'edit') {
						// F2 클릭 시 
						return true;
						
					} else if (operation == 'rename_node') {
						// 이름 변경 시
						/*
							TODO :: ajax 처리
						*/
					} else if (operation == 'delete_node') {
						// TODO :: ajax 처리
						
					} else {
						return false;
						
					}
				} 
				
				// jsTree 에 표현될 Data
				, "data": (typeof categories != 'undefined') ? categories : ''
						
				// Tree 노출 시간
				, "animation": 10
			}
		})
		.on("loaded.jstree", function(event, data) {
			console.log("loaded.jstree");
		})
		.on("select_node.jstree", function(event, data) {
			console.log(event);
			console.log("select_node.jstree");
			console.log(data);
			// $('#td-category-detail').text(data.node.text);
			$('#text-ctgr-name').val(data.node.text);
		})
		.on("open_node.jstree", function(event, data) {
			console.log("open_node.jstree");
		})
		.on("dblclick.jstree", function(event) {
			console.log("dblclick.jstree");
		})
		.bind("create.jstree", function(e, data) {
			console.log("create.jstree");
		})
		.bind("create_node.jstree", function(e, data) {
			console.log("create_node.jstree bind event");
			// 루트 카테고리를 생성한게 아니면 노드 오픈
			if (data.parent != '#') $tree.open_node(data.parent);
		})
		.bind("delete_node.jstree", function(e, data) {
			console.log("remove.jstree");
		})
		.bind("rename_node.jstree", function(e, data) {
			console.log("rename_node.jstree");
		})
		.bind("move_node.jstree", function(e, data) {
			console.log("move_node.jstree");
			console.log(data);
		});
		
		$tree = $target.jstree();
	}
	
	function get_paths(obj) {
		var paths = new Array();
		var parents = obj.parents;
		if (parents.length > 0) {
			
			paths.push(obj.text);
			
			for (var i=0; i<parents.length; ++i) {
				if (parents[i] == '#') break;
				var _$parent = $tree.get_node(parents[i]);
				paths.push(_$parent.text);
			}
			
		}
		
		return paths.reverse().join(' > ');
	}
	
	// add node
	function create_node() {
		var $parent = $tree.get_selected();
		$parent = (!$parent[0] || $parent[0].length == 0) ? '#' : $parent[0];
		
		var paths = $parent == '#' ? '' : get_paths($tree.get_node($parent));
		
		if (paths == '') {
			paths = '최상위 카테고리로 등록할 카테고리 명을 입력해 주세요.';
		} else {
			paths += '의 하위 카테고리로 등록할 카테고리 명을 입력해 주세요.';
		}
		
		var ctgrName = $('#text-ctgr-name').val();
		
		if (ctgrName == '' || !ctgrName) {
			alert('카테고리 명을 입력해 주세요.');
			return;
		}
		
		$tree.create_node($parent
						  , {"id": (1000+idx)
							  , "text": ctgrName
						  }
						  , "last"
						  , function() {
							  ++idx;
							  // SOMETHING TODO
						  }
						  , false);
		
		/* $.ajax({
			url: '/shop/admin/category/register'
			, type: 'POST'
			, dataType: 'JSON'
			, data: {
				ctgrName: ctgrName
				, prntCtgrId: ($parent == '#') ? '' : $parent
			}, success: function(payload) {
				if (payload && payload.resultYn == 'Y' && payload.ctgrId != '' && payload.ctgrName != '') {
					alert('카테고리가 생성되었습니다.');
					
					$tree.create_node($parent, {"id": payload.ctgrId
						, "text": payload.ctgrName
					   }, "last", function(payload) { console.log(payload); idx++; console.log('success node')}, false);
						
					if ($parent != '#') {
						$tree.open_node($parent);
					}
				} else {
					alert('카테고리 생성 중 오류가 발생하였습니다.');
				}
			}, error: function(xhr, status, text) {
				alert(status);
			}
		}); */
	}
	
	// delete node
	function delete_node() {
		var $selected_node = $tree.get_selected();
		$tree.delete_node($tree.get_selected(), function() {});
	}
			
</script>
    
<div class="col-sm-10">
	<table class="table table-bordered table-hover"> 
		<tr>
			<th>카테고리 목록</th>
			<th>카테고리 상세</th>
		</tr>
		<tr>
			<td id="categoryTree"></td>
			<!-- <td><a href="javascript:create_node();">카테고리 생성</a></td> -->
			<td id="td-category-detail">
				<input type="text" value="" id="text-ctgr-name" name="text-ctgr-name" />
				<input type="hidden" value="" id="hidden-ctgr-id" name="hidden-ctgr-id" />
				<input type="hidden" value="" id="hidden-prnt-ctgr-id" name="hidden-prnt-ctgr-id" />
				<a href="javascript:create_node();" class="btn btn-danger">하위 카테고리 등록</a>
				<a href="javascript:modify_node();" class="btn btn-danger">수정</a>
				<a href="javascript:delete_node();" class="btn btn-danger">삭제</a>
			</td>
		</tr>
	</table>
</div>