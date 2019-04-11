package com.github.andersori.led.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("index")
@RequestMapping("/")
public class Index {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getIndex() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
}
