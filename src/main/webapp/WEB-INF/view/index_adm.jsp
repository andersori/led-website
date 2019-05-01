<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%></title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
		        <ul class="navbar-nav mr-auto">
		            <li class="nav-item active">
		                <a class="nav-link" href="#">Início</a>
		            </li>
		        </ul>
		    </div>
		    <div class="mx-auto order-0">
		        <a class="navbar-brand mx-auto" href="${pageContext.request.contextPath}">
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
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" name="cadastrar">
				<input type="submit" value="Cadastrar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/equipe';">
				<input type="submit" value="Cadastrar Usuario" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/usuario';">
				<input type="submit" value="Cadastrar Aluno" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/aluno';">
				<input type="submit" value="Cadastrar Turma" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/turma';">
				<input type="submit" value="Cadastrar Semestre" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/semestre';">
				<input type="submit" value="Cadastrar Maratona" onclick="javascript: form.action='${pageContext.request.contextPath}/cadastrar/maratona';">
			</form>
			<br>
			<form method="post" name="listar">
				<input type="submit" value="Listar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/listar/equipe';">
				<input type="submit" value="Listar Maratonas" onclick="javascript: form.action='${pageContext.request.contextPath}/listar/maratona';">
				<input type="submit" value="Listar Alunos" onclick="javascript: form.action='${pageContext.request.contextPath}/listar/alunos';">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>