package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("index")
@RequestMapping("/")
public class Index {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIndex(Model model, HttpServletRequest request) {
		return "index";
	}
}
