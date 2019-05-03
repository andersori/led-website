<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Editar Aluno</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" action="${pageContext.request.contextPath}/editar/aluno/${id_aluno}">
				<label>Nome:</label>
				<input type="text" name="nomeAluno" value="${nomeAluno}">
				
				<label>Matricula:</label>
				<input type="text" name="matriculaAluno" value="${matriculaAluno}">
				
				<select name="turmaAluno" required="required">
					<c:forEach var="turma" items="${turmas}">
						<option value="${turma.id}" <c:if test="${turma.id eq id_turma}">selected="selected"</c:if>>${turma.nome}::${turma.curso}</option>
					</c:forEach>
				</select>
				
				<select name="equipeAluno" required="required">
					<option value="-1" disabled="disabled">Escolha uma Equipe</option>
					<c:forEach var="equipe" items="${equipes}">
						<option value="${equipe.id}" <c:if test="${equipe.id eq id_equipe}">selected="selected"</c:if>>${equipe.usuario.nome}</option>
					</c:forEach>
				</select>
								
				<input type="submit" value="Editar">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>