package com.github.andersori.led.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("listar")
@RequestMapping("/Listar")
public class Listar {
	
	@RequestMapping(value = "/Equipe", method=RequestMethod.GET)
	public String listarEquipes() {
		return "listar_equipes";
	}
	
}
