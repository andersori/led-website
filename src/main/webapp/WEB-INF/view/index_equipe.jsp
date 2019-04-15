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
			<p>${msg}</p>
		</c:if>
		<b>Equipe: ${usuario.nome}</b>
		<br>			
		<b>Casa: ${equipe.casa}</b>
		<form method="post" action="${pageContext.request.contextPath}/sair">
			<input type="submit" value="Sair">
		</form>
		<br>
		<form method="post" action="${pageContext.request.contextPath}/escolherCasa/${equipe.id}">
			<input type="submit" value="Definir Casa">
		</form>
	</body>
</html>