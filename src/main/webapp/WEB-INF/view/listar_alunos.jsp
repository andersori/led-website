<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Lista de Equipes</title>
	</head>
	<body>
	
		<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
		
		<div class="container">
			<c:if test="${not empty msg}">
				${msg}
			</c:if>
			<form method="post" action="${pageContext.request.contextPath}/listar/alunos">
				<label>*Turma</label>
				<select name="turma" required="required">
					<option value="-1" <c:if test="${turmaSelecionada eq -1}"> selected="selected" </c:if>>
					TODAS
					</option>
					<c:forEach var="turma" items="${turmas}">
						<option value="${turma.id}" <c:if test="${turmaSelecionada eq turma.id}"> selected="selected" </c:if>>
						${turma.nome} - ${turma.semestre.numSemestre}.${turma.semestre.ano}
						</option>
					</c:forEach>
				</select>
				<input type="submit" value="Pesquisar">
			</form>
			<h3>Alunos(as) Cadastrados(as)</h3>
			<ul>
				<c:forEach var="aluno" items="${alunos}">
					<li>${aluno.matricula} - ${aluno.nome} <a href="${pageContext.request.contextPath}/editar/aluno/${aluno.id}">Editar</a></li>
				</c:forEach>
			</ul>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>