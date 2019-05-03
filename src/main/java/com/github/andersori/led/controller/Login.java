package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Usuario;
import com.github.andersori.led.util.TokenGenerator;

@Controller("login")
@RequestMapping("/login")
public class Login {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLogin( @RequestParam(name = "username", required = false) String username, 
							@RequestParam(name = "senha", required = false) String senha,
							Model model,
							HttpServletRequest request) {
				
		HttpSession session = request.getSession();
		boolean infoCorreta = true;
		
		if(username != null && senha != null) {
			UsuarioDAO dao = new UsuarioHib();
			Usuario userBanco = dao.get(username);
			
			if(userBanco != null) {
				if(BCrypt.checkpw(senha, userBanco.getSenha())) {
					userBanco.setToken(TokenGenerator.generateToken(username));
					dao.update(userBanco);
					
					UsuarioBean userBean = new UsuarioBean();
					userBean.toBean(userBanco);
					session.setAttribute("usuario", userBean);
					return "redirect:" + request.getContextPath() + "/";
				}
				else {
					infoCorreta = false;
				}
			}
			else {
				infoCorreta = false;
			}
		}
		
		
		if(!infoCorreta) {
			model.addAttribute("msg", "Informações erradas");
		}
		
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postLogin(@RequestParam(name = "username", required = false) String username, 
							@RequestParam(name = "senha", required = false) String senha,
							Model model,
							HttpServletRequest request) {
		return getLogin(username, senha, model, request);
	}
}
