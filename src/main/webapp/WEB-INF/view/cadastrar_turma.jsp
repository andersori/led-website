<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Turma</title>
	</head>
	<body>
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
		<br>
		
		<h3>Turmas Cadastradas</h3>
		<ul>
			<c:forEach var="turma" items="${turmas}">
				<li>${turma.nome} de ${turma.curso}</li>
			</c:forEach>
		</ul>
	</body>
</html>