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
			<form method="POST" id="formPesquisa" action="${pageContext.request.contextPath}/listar/equipe/">
				<label>*Semestre</label>
				<select name="maratonaLis" required="required">
					<option value="ALL" selected="selected">TODAS</option>
					<c:forEach var="maratona" items="${maratonas}">
						<option value="${maratona.id}">${maratona.semestre.ano}.${maratona.semestre.numSemestre} em ${maratona.data}</option>
					</c:forEach>
				</select>
				<label>*Casa</label>
				<select name="casaLis" required="required">
					<option value="ALL">TODAS</option>
					<option value="INDEFINIDO">INDEFINIDO</option>
					<option value="GRIFINORIA">GRIFINORIA</option>
					<option value="CORVINAL">CORVINAL</option>
					<option value="SONSERINA">SONSERINA</option>
					<option value="LUFALUFA">LUFALUFA</option>
				</select>
				<input type="submit" value="Pesquisar" onclick="pesquisar()" />
			</form>
			<h3>Equipes Cadastradas</h3>
			<ul>
				<c:forEach var="equipe" items="${equipes}">
					<li>${equipe.usuario.nome} <a href="${pageContext.request.contextPath}/editar/equipe/${equipe.id}">Editar</a></li>
				</c:forEach>
			</ul>
		</div>
	</body>
	<script type="text/javascript">
		function pesquisar(){
			let form = document.getElementById("formPesquisa");
			let selects = document.getElementsByTagName("select");
			
			let url = selects[0].value + '/';
			url += selects[1].value;
			
			form.action += url;
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>