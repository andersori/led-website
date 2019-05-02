<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Cadastrar Equipe</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" action="${pageContext.request.contextPath}/cadastrar/equipe">
				<label>*Nome:</label>
				<input type="text" name="nomeEquipe" required="required" value="${nome}">
				
				<label>Email:</label>
				<input type="text" name="emailEquipe" value="${email}">
				
				<label>*Username:</label>
				<input type="text" name="usernameEquipe" required="required" value="${username}">
				
				<label>*Senha:</label>
				<input type="password" name="senhaEquipe" required="required" value="${senha}">
				
				<select name="turmaEquipe" required="required">
					<c:forEach var="turma" items="${turmas}">
						<option value="${turma.id}">${turma.nome}::${turma.curso}</option>
					</c:forEach>
				</select>
				
				<input type="submit" value="Cadastrar">
			</form>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>