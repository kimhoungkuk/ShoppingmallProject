<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<body>
    <div class="container" style="padding-top: 10px;">
        <nav class="navbar navbar-inverse"> 
            <div class="container-fluid"> 
                <div class="navbar-header">
                    <!-- 오른쪽 메뉴바 -->
                    <button type="button" class="collapsed navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span>
                    </button> 
                    <a href="/" class="navbar-brand">Nodejs</a>
                </div> 
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/">Home</a>
                        </li>
                        <li><a href="/admin/products">ADMIN</a></li>
                        <li><a href="/chat">CHAT</a></li>  
                        <li><a href="/cart">CART</a></li>  
                        <li><a href="/accounts/join">JOIN</a></li> 
                        <li><a href="/accounts/login">LOGIN</a></li>  
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/checkout/nomember">구매조회</a></li> 
                    </ul> 
                </div> 
            </div> 
        </nav>