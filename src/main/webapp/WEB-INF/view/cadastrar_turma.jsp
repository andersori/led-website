<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Cadastrar Turma</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" action="${pageContext.request.contextPath}/cadastrar/turma">
				<label>*Nome:</label>
				<input type="text" name="nomeTurma" required="required" value="${nome}">
				
				<label>*Código:</label>
				<input type="text" name="codTurma" required="required" value="${cod}">
				
				<label>*Curso:</label>
				<select name="cursoTurma" required="required">
					<option value="ES">Engenharia de Software</option>
					<option value="CC">Ciência da Computação</option>
				</select>
				
				<label>*Semestre</label>
				<select name="semestreTurma" required="required">
					<c:forEach var="semestre" items="${semestres}">
						<option value="${semestre.id}">${semestre.ano}.${semestre.numSemestre}</option>
					</c:forEach>
				</select>
				
				<input type="submit" value="Cadastrar">
			</form>
			<span>*Obrigatório</span>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>