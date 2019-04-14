<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Semestre</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form action="${pageContext.request.contextPath}/CadastrarSemestre" method="post">
			<label>*Ano</label>
			<input type="number" min="<fmt:formatDate value="${date}" pattern="yyyy" />" name="anoSemestre" value="<fmt:formatDate value="${date}" pattern="yyyy" />">
			
			<label>*Semestre</label>
			<input type="number" min="1" max="2" name="numSemestre" value="1">
			<input type="submit" value="Cadastrar">
		</form>
		<span>*Obrigatório</span>
		<br>
		<h3>Semestres Cadastrados</h3>
		<ul>
			<c:forEach var="semestre" items="${semestres}">
				<li>${semestre.ano}.${semestre.numSemestre}</li>
			</c:forEach>
		</ul>
	</body>
</html>