<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- 파일업로드 플러그인 -->
 <!--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
 -->    <link href="/shop/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/shop/themes/explorer-fa/theme.css" media="all" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="/shop/js/common.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/shop/js/plugins/sortable.js" type="text/javascript"></script>
    <script src="/shop/js/fileinput.js" type="text/javascript"></script>
    <script src="/shop/js/locales/fr.js" type="text/javascript"></script>
    <script src="/shop/js/locales/es.js" type="text/javascript"></script>
    <script src="/shop/themes/explorer-fa/theme.js" type="text/javascript"></script>
    <script src="/shop/themes/fa/theme.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" type="text/javascript"></script>
<div class="col-sm-10">
<div class="kv-main">
    <div class="page-header">
        <h1>Bootstrap File Input Example
            <small><a href="https://github.com/kartik-v/bootstrap-fileinput-samples"><i
                    class="glyphicon glyphicon-download"></i> Download Sample Files</a></small>
        </h1>
    </div>
    <div class="file-loading">
        <input id="kv-explorer" type="file" name="files" multiple>
        
    </div>
        <div class="file-loading" height="300px">
    		<input id="input-711" name="kartik-input-711[]" type="file" multiple>
		</div>
    <form enctype="multipart/form-data" action="/shop/admin/productImageInsert" method="post">
    	<c:if test="${empty list}">
    	<p>list 비이있음</p>
    	</c:if>
    	
    	<c:if test="${not empty list}">
    	<p>list 존재함</p>
    	<c:forEach items="${list }" var="tmp">
    	<p>${tmp}</p>
    	</c:forEach>
    	</c:if>
    	
    	
		<button class="btn btn-primary">Submit</button>
	</form>
    <hr>
</div>
</div>
</body>
<script>
    $(document).ready(function () {
        $("#kv-explorer").fileinput({
            'theme': 'explorer-fa',
            'uploadUrl': '/shop/admin/productImageInsert',
            overwriteInitial: false,
            zoom:false
        });
        $("#input-711").fileinput({
            uploadUrl: '/shop/admin/productImageInsertAjax',
            deleteUrl: '/shop/admin/productImageDeleteAjax',
            showZoom:true,
            showBrowse: false,
            browseOnZoneClick: true,

        });
        $("#input-711").on('fileloaded', function(event, file, previewId, index, reader) {
            console.log("fileloaded");
        });
    });
    

   
    
    
</script>
