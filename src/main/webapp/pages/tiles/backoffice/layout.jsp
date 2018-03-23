<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%   
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0); 
%>     
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
    <title><spring:message code="site.admin.title" /></title>
    <!--summernote 에디터기 -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>   
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="/resources/summernote/summernote.css" rel="stylesheet">
    <script src="/resources/summernote/summernote.js" />       
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-2.2.4.min.js" 
		integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--폰트어섬추가 -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--jquery-ui 추가 -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
		<!-- colorpicker -->
	<link rel="stylesheet" media="screen" type="text/css" href="/resources/component/colorpicker/css/colorpicker.css" />
	<script type="text/javascript" src="/resources/component/colorpicker/js/colorpicker.js" ></script>
	
</head>
<tiles:insertAttribute name="header" /> 
<tiles:insertAttribute name="menu" />  
<tiles:insertAttribute name="content" /> 
<tiles:insertAttribute name="footer" />  

