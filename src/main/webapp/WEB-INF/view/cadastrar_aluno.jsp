<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.github.andersori.led.util.Constante"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%>::Cadastrar Aluno</title>
	</head>
	<body>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/cadastrar/aluno">
			<label>*Nome:</label>
			<input type="text" name="nomeAluno" required="required" value="${nome}">
			
			<label>*Matricula:</label>
			<input type="text" name="matriculaAluno" required="required" value="${cod}">
			
			<select name="turmaAluno" required="required">
				<c:forEach var="turma" items="${turmas}">
					<option value="${turma.id}">${turma.nome}::${turma.curso}</option>
				</c:forEach>
			</select>
							
			<input type="submit" value="Cadastrar">
		</form>
		<span>*Obrigatório</span>
	</body>
</html>