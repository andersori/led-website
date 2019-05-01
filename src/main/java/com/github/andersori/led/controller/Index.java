package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.entity.Permissao;

@Controller("index")
@RequestMapping("/")
public class Index {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIndex(	@RequestParam(name="msg", required=false) String msg,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user != null) {
			if(user.getPermissao() == Permissao.EQUIPE) {
				EquipeDAO dao = new EquipeHib();
				EquipeBean beanEquipe = new EquipeBean();
				beanEquipe.toBean(dao.get(user.toEntity()));
				
				model.addAttribute("equipe", beanEquipe);
				
				if(msg != null)
				{
					model.addAttribute("msg", msg);
				}
			    
				return "index_equipe";
			}
		}
		return "index_adm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postIndex(@RequestParam(name="msg", required=false) String msg,
							Model model, HttpServletRequest request) {
		return getIndex(msg, model, request);
	}
	
	@GetMapping("favicon.ico")
	@ResponseBody
	public String getFavicon(HttpServletRequest request) {
		return request.getContextPath()+"/resources/img/favicon.ico";
	}
}
