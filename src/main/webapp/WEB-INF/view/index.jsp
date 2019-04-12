<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="resources/css/style.css" rel="text/css" />
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Index</title>
	</head>
	<body>
		INDEX
		<br>
		<a href="${pageContext.request.contextPath}/Sair">Sair</a>
		<br>
		<form method="post" name="cadastrar">
			<input type="submit" value="Cadastrar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarEquipe';">
			<input type="submit" value="Cadastrar Administrador" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarADM';">
			<input type="submit" value="Cadastrar Membro" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarMembro';">
			<input type="submit" value="Cadastrar Turma" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarTurma';">
		</form>
		<br>
		<form method="post" name="editar">
			<input type="submit" value="Editar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/EditarEquipe';">
			<input type="submit" value="Editar Administrador" onclick="javascript: form.action='${pageContext.request.contextPath}/EditarADM';">
		</form>
	</body>
</html>