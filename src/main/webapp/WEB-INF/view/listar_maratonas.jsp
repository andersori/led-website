<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Lista de Maratonas</title>
	</head>
	<body>
		<h3>Maratonas Cadastradas</h3>
		<ul>
			<c:forEach var="maratona" items="${maratonas}">
				<li>${maratona.id} - ${maratona.semestre.ano}.${maratona.semestre.numSemestre}[${maratona.data}] <a href="${pageContext.request.contextPath}/editar/maratona/${maratona.id}">Editar</a></li>
			</c:forEach>
		</ul>
	</body>
</html>