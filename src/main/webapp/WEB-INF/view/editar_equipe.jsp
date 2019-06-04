<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ page import="com.github.andersori.led.entity.Casa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Editar Equipe</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" action="${pageContext.request.contextPath}/editar/equipe/${id_equipe}">
				<label>Nome:</label>
				<input type="text" name="nomeEquipe" value="${nome}">
				
				<label>Username:</label>
				<input type="text" name="usernameEquipe" value="${username}">
				
				<label>Pontos:</label>
				<input type="number" min="0" name="pontosEquipe" value="${pontos}">
				
				<label>Senha:</label>
				<input type="password" name="senhaEquipe" >
				
				<select name="turmaEquipe" required="required">
					<option value="-1" disabled="disabled">Selecione uma Turma</option>
					<c:forEach var="turma" items="${turmas}">
						<option value="${turma.id}" <c:if test="${turma.id eq id_turma}">selected="selected"</c:if>>${turma.nome}::${turma.curso}</option>
					</c:forEach>
				</select>
				
				<select name="maratonaEquipe" required="required">
					<option value="-1" disabled="disabled">Selecione uma Maratona</option>
					<c:forEach var="maratona" items="${maratonas}">
						<option value="${maratona.id}" <c:if test="${maratona.id eq id_maratona}">selected="selected"</c:if>>${maratona.semestre.ano}::${maratona.semestre.numSemestre}</option>
					</c:forEach>
				</select>
    
				<select name="casaEquipe" required="required">
					<option value="-1" disabled="disabled">Selecione uma Casa</option>
					<option value="${Casa.INDEFINIDO.getId()}" <c:if test="${casa eq Casa.INDEFINIDO.getId()}">selected="selected"</c:if> ></option>
					<option value="${Casa.GRIFINORIA.getId()}" <c:if test="${casa eq Casa.GRIFINORIA.getId()}">selected="selected"</c:if> ></option>
					<option value="${Casa.CORVINAL.getId()}" <c:if test="${casa eq Casa.CORVINAL.getId()}">selected="selected"</c:if> ></option>
					<option value="${Casa.SONSERINA.getId()}" <c:if test="${casa eq Casa.SONSERINA.getId()}">selected="selected"</c:if> ></option>
					<option value="${Casa.LUFALUFA.getId()}" <c:if test="${casa eq Casa.LUFALUFA.getId()}">selected="selected"</c:if> ></option>
				</select>
				
				<input type="submit" value="Editar">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>