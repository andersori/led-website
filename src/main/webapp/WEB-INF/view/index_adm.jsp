<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%></title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/sair">
			<input type="submit" value="Sair">
		</form>
		<br>
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
	</body>
</html>