<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Login</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<div class="login">
			<h1>Sign in to <%=Constante.getAppName()%></h1>
			<form method="post" action="${pageContext.request.contextPath}/login">
				<input type="text" placeholder="username" name="username" required="required">
				<input type="password" placeholder="******" name="senha" required="required">
				<button class="btn_login">Sign in</button>
			</form>
		</div>
	</body>
</html>