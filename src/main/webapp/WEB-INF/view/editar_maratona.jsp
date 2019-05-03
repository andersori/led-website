<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Editar Maratona</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form action="${pageContext.request.contextPath}/editar/maratona/${id_maratona}" method="post">
				<label>Semestre</label>
				<select name="semestreMaratona" required="required">
					<c:forEach var="semestre" items="${semestres}">
						<option value="${semestre.id}" <c:if test="${semestre.id eq id_semestre}">selected="selected"</c:if>>${semestre.ano}.${semestre.numSemestre}</option>
					</c:forEach>
				</select>
				<label>*Data</label>
				<input type="date" required="required" name="dataMaratona" value="${dataMaratona}"/>
				<input type="submit" value="Editar">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>