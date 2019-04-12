package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("cadastrarUsuario")
@RequestMapping("/CadastrarUsuario")
public class CadastrarUsuario {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(Model model, HttpServletRequest request) {
		return "cadastrar_usuario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage(Model model, HttpServletRequest request) {
		return getPage(model, request);
	}
}
