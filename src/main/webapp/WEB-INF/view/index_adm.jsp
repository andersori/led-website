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
		
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
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
				<input type="submit" value="Listar Usuarios" onclick="javascript: form.action='${pageContext.request.contextPath}/listar/usuarios';">
				<input type="submit" value="Listar Turmas" onclick="javascript: form.action='${pageContext.request.contextPath}/listar/turmas';">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>