<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%></title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/Sair">
			<input type="submit" value="Sair">
		</form>
		<br>
		<form method="post" name="cadastrar">
			<input type="submit" value="Cadastrar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Equipe';">
			<input type="submit" value="Cadastrar Usuario" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Usuario';">
			<input type="submit" value="Cadastrar Aluno" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Aluno';">
			<input type="submit" value="Cadastrar Turma" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Turma';">
			<input type="submit" value="Cadastrar Semestre" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Semestre';">
			<input type="submit" value="Cadastrar Maratona" onclick="javascript: form.action='${pageContext.request.contextPath}/Cadastrar/Maratona';">
		</form>
		<br>
		<form method="post" name="editar">
			<input type="submit" value="Editar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/Editar/Equipe/1';">
			<input type="submit" value="Editar Administrador" onclick="javascript: form.action='${pageContext.request.contextPath}/Editar/Usuario/1';">
		</form>
	</body>
</html>