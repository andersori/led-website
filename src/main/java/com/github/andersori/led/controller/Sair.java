package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("sair")
@RequestMapping("/Sair")
public class Sair {
	
	@RequestMapping(method = RequestMethod.GET)
	public String sair(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("usuario");
		
		return "redirect:"+request.getContextPath()+"/Login";
	}
}
