<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<meta charset="UTF-8">
		<title><%=Constante.getAppName()%>::Lista de Equipes</title>
	</head>
	<body>
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
	</body>
</html>