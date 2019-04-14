<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Usuario</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/CadastrarUsuario">
			<label>*Nome:</label>
			<input type="text" name="nomeUsuario" required="required" value="${nome}">
			
			<label>Email:</label>
			<input type="text" name="emailUsuario" value="${email}">
			
			<label>*Username:</label>
			<input type="text" name="usernameUsuario" required="required" value="${username}">
			
			<label>*Senha:</label>
			<input type="password" name="senhaUsuario" required="required" value="${senha}">
			
			<input type="submit" value="Cadastrar">
		</form>
		<span>*Obrigatório</span>
	</body>
</html>