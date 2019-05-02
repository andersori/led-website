<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-equipe.css" rel="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%></title>
	</head>
	<body class="body-equipe">
		<div class="meu-container">
			
			<c:import url="nav_bar.jsp" charEncoding="UTF-8"></c:import>
			
			<c:if test="${equipe.casa.id == 0}"><!-- 0 significa indefinido -->
				<div class="content-escolha">
					<h1>${usuario.nome}</h1>
					<div class="dialogo" id="dialogo-text">
						<p>...</p>
					</div>
					<div class="chapel">
						<div class="img-chapel">
							<img src="${pageContext.request.contextPath}/resources/img/chapel.png">
						</div>
						
						<c:if test="${not empty msg}">
							<div class="msg">
								<p>${msg}</p>
							</div>
						</c:if>
						
						<div class="form-escolha">
							<button class="btn-escolha" id="btn-escolha" onclick="escolherEquipe()">Definir Casa</button>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</body>
	<script>
		(function(){
	
			let escolha_div = document.getElementsByClassName('content-escolha')[0];
	
			if(escolha_div){
				let btn = document.getElementById('btn-escolha');
				btn.setAttribute('disabled','');
	
				let frases_boas_inicias = [
					['...', 1000],
					['Umm!', 1000],
					['Parece que você não faz parte de uma casa ainda.', 2500],
					['Vamos não perca tempo, deixe-me te analisar para decidir em qual casa devo te colocar!', 3000]
				];
	
	
				let dialogo = document.getElementById('dialogo-text');
				dialogo = dialogo.getElementsByTagName('p')[0];
	
				function timeout(i) {
					if(i < frases_boas_inicias.length){						
						setTimeout(function () {
							dialogo.textContent = frases_boas_inicias[i][0];
							timeout(++i);
						}, frases_boas_inicias[i][1]);
					}
					else{
						btn.removeAttribute('disabled');
					}
				}
				timeout(0);//Primeira frase exibida
	
			}
	
		})()
	
		function escolherEquipe(){
	
			let escolha_div = document.getElementsByClassName('content-escolha')[0];
	
			if(escolha_div){
				let btn = document.getElementById('btn-escolha');
				btn.setAttribute('disabled','')
				
				let dialogo = document.getElementById('dialogo-text');
				let frases_do_chapeu = [
					['Ahh..', 1400],
					['Uhmmm...', 1400],
					['Muito bem..', 1000],
					['Eu sei muito bem o que fazer com você..', 1800],
					['Difícil... Muito difícil...', 1800],
					['Tem muita coragem e uma mente nada mau também...', 2200],
					['Tem talento, se tem...', 1800],
					['Não tenho certeza se te coloco na Sonserina...', 2000],
					['Creio que Lufa Lufa não seria uma má ideia pra você...', 2800]
				]
	
	
				dialogo = dialogo.getElementsByTagName('p')[0];
				dialogo.textContent = 'Deixe-me ver...';
	
				function timeout(i) {
					if(i > 0){
						let num_frase = Math.floor(Math.random() * frases_do_chapeu.length);
	
						setTimeout(function () {
							dialogo.textContent = frases_do_chapeu[num_frase][0];
							frases_do_chapeu.splice(num_frase,1)
							timeout(--i);
						}, frases_do_chapeu[num_frase][1]);
					}
					else {
	
						setTimeout(function(){
	
							let div_form = document.getElementsByClassName('form-escolha')[0];
							let form = document.createElement('form');
							form.action = "${pageContext.request.contextPath}"+'escolherCasa/'+"${equipe.id}";
							form.method = 'post'
							div_form.append(form);
							form.submit();
							
						}, 2800)
	
					}
				}
				timeout(4);//Qtd de frases exibidas
			}
	
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>