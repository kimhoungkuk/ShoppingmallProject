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
				 	operation : tree method name
				 	node : get to node info from first argument
				 	parent : parent info for node
				 	position : custom information (second argument)
				*/
				"check_callback": function(operation, node, parent, position, more) {
					
					/* console.log('callback', operation);
					console.log('node', node); */
					
					// drag & drop
					if (operation == 'move_node') {
						// parent 가 변경 된다면 데이터 및 구조가 깨지므로 같은 카테고리에서의 순서만 변경 가능
						if (node.parent != parent.id) {
							return false;
						} else {
							// 노드 D&D 중에는 타켓 노드를 알 수 없음므로 bind method 에서 실제 업데이트 처리한다.
							return true;
						}
						
					} else if (operation == 'create_node') {
						
						var _is_success = false;
						
						// 노드 생성 시
						$.ajax({
							url: '/admin/category/register'
							, type: 'POST'
							, dataType: 'JSON'
							, async: false
							, data: {
								ctgrName: node.text
								, prntCtgrId: (parent.id == '#') ? '9999' : parent.id
							}, success: function(payload) {
								if (payload && payload.resultYn == 'Y' && payload.ctgrId != '' && payload.ctgrName != '') {
									console.log('return true');
									alert('카테고리가 생성되었습니다.');
									node.id = payload.ctgrId;
									_is_success = true;
								} else {
									console.log('return false');
									alert('카테고리 생성 중 오류가 발생하였습니다.');
								}
							}, error: function(xhr, status, text) {
								console.log('return false');
								alert(status);
							}
						});
						
						return _is_success;
						
					} else if (operation == 'edit') {
						// F2 클릭 시 
						return true;
						
					} else if (operation == 'rename_node') {
						
						var _is_success = false;
						
						$.ajax({
							url: '/admin/category/update/rename/' + node.id
							, type: 'GET'
							, dataType: 'JSON'
							, async: false
							, data: {
								ctgrName: encodeURIComponent(position)
							}, success: function(payload) {
								if (payload && payload.resultYn == 'Y') {
									alert('카테고리명이 수정되었습니다.');
									_is_success = true;
								} else {
									alert((payload && payload.resultMsg) ? payload.resultMsg : '처리 중 오류가 발생하였습니다.');
								}
							}, error: function(xhr, status, text) {
								alert(text);
							}
						});
						
						return _is_success;
						
					} else if (operation == 'delete_node') {
						var _is_success = false;
						
						$.ajax({
							url: '/admin/category/update/useYn/' + node.id
							, type: 'GET'
							, dataType: 'JSON'
							, async: false
							, data: {
								useYn: 'N'
							}, success: function(payload) {
								if (payload && payload.resultYn == 'Y') {
									alert('카테고리가 삭제되었습니다.');
									_is_success = true;
								} else {
									alert((payload && payload.resultMsg) ? payload.resultMsg : '처리 중 오류가 발생하였습니다.');
								}
							}, error: function(xhr, status, text) {
								alert(text);
							}
						});
						
						return _is_success;
						
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
			console.log("select_node.jstree");
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
			console.log(data);
		})
		.bind("move_node.jstree", function(e, data) {
			console.log("move_node.jstree");
			console.log(data);
			
			if (data.old_position == data.position) return;
			
			// 노출 순서 변경
			$.ajax({
				url: '/admin/category/update/dispOrder/' + data.node.id
				, type: 'POST'
				, dataType: 'JSON'
				, async: false
				, data: {
					prntCtgrId: (data.parent == '#') ? '9999' : data.parent
					, dispOrder: data.old_position
					, destDispOrder: data.position
				}, success: function(payload) {
					if (payload && payload.success == 'Y') {
						console.log('success');
					}
					if (!payload || payload.success == 'N') alert('카테고리 순서 변경 중 오류가 발생하였습니다.');
					
				}, error: function(xhr, status, text) {
					alert(text);
				}
			});
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
		var _parent_ = $tree.get_selected();
		_parent_ = (!_parent_[0] || _parent_[0].length == 0) ? '#' : _parent_[0];
		
		var paths = _parent_ == '#' ? '' : get_paths($tree.get_node(_parent_));
		
		if (paths == '') {
			paths = '최상위 카테고리로 등록할 카테고리 명을 입력해 주세요.';
		} else {
			paths += '의 하위 카테고리로 등록할 카테고리 명을 입력해 주세요.';
		}
		
		var ctgrName = $.trim($('#text-ctgr-name').val());
		
		if (ctgrName == '' || !ctgrName) {
			alert('카테고리 명을 입력해 주세요.');
			return;
		}
		// create_node definition :: ([parent, node, pos, callback, is_loaded])]
		$tree.create_node(_parent_
						  , {"id": ''
							  , "text": ctgrName
						  }
						  , "last"
						  , function() {}
						  , false);
		
		/* $.ajax({
			url: '/admin/category/register'
			, type: 'POST'
			, dataType: 'JSON'
			, data: {
				ctgrName: ctgrName
				, prntCtgrId: (_parent_ == '#') ? '' : _parent_
			}, success: function(payload) {
				if (payload && payload.resultYn == 'Y' && payload.ctgrId != '' && payload.ctgrName != '') {
					alert('카테고리가 생성되었습니다.');
					
					$tree.create_node(_parent_, {"id": payload.ctgrId
						, "text": payload.ctgrName
					   }, "last", function(payload) { console.log(payload); idx++; console.log('success node')}, false);
						
					if (_parent_ != '#') {
						$tree.open_node(_parent_);
					}
				} else {
					alert('카테고리 생성 중 오류가 발생하였습니다.');
				}
			}, error: function(xhr, status, text) {
				alert(status);
			}
		}); */
	}
	
	// rename_node node
	function rename_node() {
		var _current_ = $tree.get_selected();
		_current_ = (!_current_[0] || _current_[0].length == 0) ? '#' : _current_[0];
		
		var ctgrName = $('#text-ctgr-name').val();
		
		if (ctgrName == '' || !ctgrName) {
			alert('카테고리 명을 입력해 주세요.');
			return;
		}
		
		if (confirm('카테고리명을 수정하시겠습니까?')) {
			// rename_node definition :: (obj, val)
			$tree.rename_node(_current_
							  , ctgrName);
		}
	}
	
	// delete node
	function delete_node() {
		var _current_ = $tree.get_selected();
		_current_ = (!_current_[0] || _current_[0].length == 0) ? '#' : _current_[0];
		
		if (confirm('미사용 처리 하시겠습니까?')) {
			// delete_node definition :: (obj)
			$tree.delete_node(_current_);
		}
		
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
				<a href="javascript:rename_node();" class="btn btn-danger">수정</a>
				<a href="javascript:delete_node();" class="btn btn-danger">삭제</a>
			</td>
		</tr>
	</table>
</div>