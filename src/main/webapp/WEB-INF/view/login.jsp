<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="resources/css/style.css" rel="text/css" />
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Login</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/Login">
			<input type="text" placeholder="username" name="username" required="required">
			<input type="password" placeholder="******" name="senha" required="required">
			<input type="submit" value="Entrar">
		</form>
	</body>
</html>