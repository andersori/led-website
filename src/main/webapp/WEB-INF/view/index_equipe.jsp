<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.github.andersori.led.util.Constante" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-equipe.css" rel="text/css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"/>
		<title><%=Constante.getAppName()%></title>
	</head>
	<body class="body-equipe">
		<div class="container">
			<div class="content-escolha">
				<h1>${usuario.nome}</h1>
				<div class="dialogo" id="dialogo-text">
					<p>...</p>
				</div>
				<div class="chapel">
					<div class="img-chapel">
						<img src="${pageContext.request.contextPath}/resources/img/chapel.png">
					</div>
					
					<% 
						Map<String, Object> modelMap = (HashMap<String, Object>) request.getAttribute("parameters");
						if(modelMap.get("msg") != null){
					%>
							<div class="msg">
								<p><%= modelMap.get("msg") %></p>
							</div>
					<%
						}
					%>

					<div class="form-escolha">
						<button class="btn-escolha" id="btn-escolha" onclick="escolherEquipe()">Definir Casa</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
		(function(){
	
			let escolha_div = document.getElementsByClassName('content-escolha')[0];
	
			if(escolha_div){
				let btn = document.getElementById('btn-escolha');
				btn.setAttribute('disabled','');
	
				let frases_boas_inicias = [
					'...',
					'Umm!',
					'Parece que você não faz parte de uma casa ainda.',
					'Vamos não perca tempo, deixe-me te analisar para que você possa fazer parte de uma equipe!'
				];
	
	
				let dialogo = document.getElementById('dialogo-text');
				dialogo = dialogo.getElementsByTagName('p')[0];
	
				function timeout(i) {
					if(i < frases_boas_inicias.length){						
						setTimeout(function () {
							dialogo.textContent = frases_boas_inicias[i];
							timeout(++i);
						}, Math.floor((frases_boas_inicias[i].length / 15)) * 500);
					}
					else{
						btn.removeAttribute('disabled');
					}
				}
				timeout(0);
	
			}
	
		})()
	
		function escolherEquipe(){
	
			let escolha_div = document.getElementsByClassName('content-escolha')[0];
	
			if(escolha_div){
				let btn = document.getElementById('btn-escolha');
				btn.setAttribute('disabled','')
				
				let dialogo = document.getElementById('dialogo-text');
				let frases_do_chapeu = [
					'Ahh..',
					'Uhmmm...',
					'Muito bem..',
					'Eu sei muito bem o que fazer com você..',
					'Difícil... Muito difícil...',
					'Tem muita coragem e uma mente nada mau também...',
					'Tem talento, se tem...',
					'Não tenho certeza se te coloco na Sonserina...',
					'Creio que Lufa Lufa não seria uma má ideia pra você...'
				]
	
	
				dialogo = dialogo.getElementsByTagName('p')[0];
				dialogo.textContent = 'Deixe-me ver...';
	
				function timeout(i) {
					if(i > 0){
						let num_frase = Math.floor(Math.random() * frases_do_chapeu.length);
						let tempo = 2000;
	
						setTimeout(function () {
							dialogo.textContent = frases_do_chapeu[num_frase];
							frases_do_chapeu.splice(num_frase,1)
							timeout(--i);
						}, tempo);
					}
					else {
	
						let tempo = 2000;
	
						setTimeout(function(){
	
							let div_form = document.getElementsByClassName('form-escolha')[0];
							let form = document.createElement('form');
							form.action = "${pageContext.request.contextPath}"+'escolherCasa/'+"${equipe.id}";
							form.method = 'post'
							div_form.append(form);
							form.submit();
							
						}, tempo)
	
					}
				}
				timeout(4);
			}
	
		}
	</script>
</html>