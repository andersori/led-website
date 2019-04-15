package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.util.CasaSeletor;

@Controller("escolherCasa")
@RequestMapping("/escolherCasa")
public class EscolherCasa {
	
	@RequestMapping(value = "/{equipeId}", method=RequestMethod.GET)
	public String getPage(	@PathVariable Long equipeId,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		EquipeDAO daoEquipe = new EquipeHib();
		try {
			Equipe equipe = daoEquipe.get(equipeId);
			if(!equipe.getUsuario().getUsername().equals(user.getUsername())) {
				model.addAttribute("msg", "Não tente fazer isso denovo.");
			} else {
				if(equipe.getCasa() == Casa.INDEFINIDO) {
					if(equipe.getMaratona() != null){
						try {
							equipe = CasaSeletor.getEquipe(equipeId);
							
							model.addAttribute("equipe", equipe);
							model.addAttribute("msg", "Agora você faz parte da casa '"+equipe.getCasa()+"'");
							
						} catch(Exception e) {
							e.printStackTrace();
							model.addAttribute("msg", "Não foi possivel definir uma casa para você, tente novamente.");
						}
					} else {
						model.addAttribute("msg", "Você não foi cadastrado em uma maratona.");
					}
					
				}else {
					model.addAttribute("msg", "Você já possui uma casa.");
				}
			}
			
		} catch(Exception e) {
			model.addAttribute("msg", "Equipe não encontrada");
		}
		
		return "forward:/";
	}
	
	@RequestMapping(value = "/{equipeId}", method=RequestMethod.POST)
	public String postPage(	@PathVariable Long equipeId,
							Model model, HttpServletRequest request) {
		
		return getPage(equipeId, model, request);
	}
}
