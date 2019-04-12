package com.github.andersori.led.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Usuario;

@WebFilter("/*")
public class Autenticado implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)  request;	
        HttpServletResponse res = (HttpServletResponse) response;	
        HttpSession session = req.getSession();
        
		System.out.println("Requisitando: " + req.getRequestURI());
		
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		String loginURI = req.getContextPath() + "/Login";
		
		boolean logado = false;
		boolean loginRequest = req.getRequestURI().equals(loginURI);
		
		if(user != null) {
			Usuario userBanco = new UsuarioHib().get(user.getUsername());
			if(userBanco != null && user.getToken().equals(userBanco.getToken())) {
				logado = true;
			}
		}
		
		if(logado || loginRequest) {
			chain.doFilter(request, response);
		}
		else {
			session.removeAttribute("usuario");
			res.sendRedirect(req.getContextPath()+"/Login");
		}
		
		
	}

}
