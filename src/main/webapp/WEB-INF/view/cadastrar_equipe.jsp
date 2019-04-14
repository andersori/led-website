<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Equipe</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/CadastrarEquipe">
			<label>*Nome:</label>
			<input type="text" name="nomeEquipe" required="required" value="${nome}">
			
			<label>Email:</label>
			<input type="text" name="emailEquipe" value="${email}">
			
			<label>*Username:</label>
			<input type="text" name="usernameEquipe" required="required" value="${username}">
			
			<label>*Senha:</label>
			<input type="password" name="senhaEquipe" required="required" value="${senha}">
			
			<select name="turmaEquipe">
				<c:forEach var="turma" items="${turmas}">
					<option value="${turma.id}">${turma.nome}::${turma.curso}</option>
				</c:forEach>
			</select>
			
			<input type="submit" value="Cadastrar">
		</form>
		<br>
		<h3>Equipes Cadastradas</h3>
		<ul>
			<c:forEach var="equipe" items="${equipes}">
				<li>${equipe.usuario.nome} de ${equipe.turma.nome}</li>
			</c:forEach>
		</ul>
	</body>
</html>