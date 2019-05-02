<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Início</a>
            </li>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="${pageContext.request.contextPath}/">
        	<img src="${pageContext.request.contextPath}/resources/img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
			<%=Constante.getAppName()%>
		</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/sair">Sair</a>
            </li>
        </ul>
    </div>
</nav>