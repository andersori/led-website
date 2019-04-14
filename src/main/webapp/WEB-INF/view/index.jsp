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
		<c:if test="${sessionScope.usuario.permissao == 'ADM'}">
			<form method="post" action="${pageContext.request.contextPath}/Sair">
				<input type="submit" value="Sair">
			</form>
			<br>
			<form method="post" name="cadastrar">
				<input type="submit" value="Cadastrar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarEquipe';">
				<input type="submit" value="Cadastrar Usuario" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarUsuario';">
				<input type="submit" value="Cadastrar Aluno" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarAluno';">
				<input type="submit" value="Cadastrar Turma" onclick="javascript: form.action='${pageContext.request.contextPath}/CadastrarTurma';">
			</form>
			<br>
			<form method="post" name="editar">
				<input type="submit" value="Editar Equipe" onclick="javascript: form.action='${pageContext.request.contextPath}/EditarEquipe';">
				<input type="submit" value="Editar Administrador" onclick="javascript: form.action='${pageContext.request.contextPath}/EditarUsuario';">
			</form>
		</c:if>
		<c:if test="${sessionScope.usuario.permissao == 'EQUIPE'}">
			<b>Equipe: ${usuario.nome}</b>
			<br>			
			<b>Casa: ${equipe.casa}</b>
			<form method="post" action="${pageContext.request.contextPath}/Sair">
				<input type="submit" value="Sair">
			</form>
			<br>
			<form method="post" action="${pageContext.request.contextPath}/EscolherCasa">
				<input type="submit" value="Definir Casa">
			</form>
		</c:if>
	</body>
</html>