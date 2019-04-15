package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("editar")
@RequestMapping("/Editar")
public class Editar {
	
	@RequestMapping(value = "/Equipe/{idEquipe}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarEquipe(	@PathVariable Long idEquipe, 
								Model model, HttpServletRequest request) {
		
		return "editar_equipe";
	}
	
	@RequestMapping(value = "/Usuario/{idUsuario}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarUsuario(@PathVariable Long idUsuario, 
								Model model, HttpServletRequest request) {
		
		return "editar_usuario";
	}
}
