<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Maratona</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>
		<form action="${pageContext.request.contextPath}/CadastrarMaratona" method="post">
			<label>*Semestre</label>
			<select name="semestreMaratona" required="required">
				<c:forEach var="semestre" items="${semestres}">
					<option value="${semestre.id}">${semestre.ano}.${semestre.numSemestre}</option>
				</c:forEach>
			</select>
			<label>*Data</label>
			<input type="date" required="required" name="dataMaratona"/>
			<input type="submit" value="Cadastrar">
		</form>
	</body>
</html>