package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.MaratonaBean;
import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.MaratonaDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.dao.hibernate.MaratonaHib;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Permissao;

@Controller("listar")
@RequestMapping("/listar")
public class Listar {
	
	@RequestMapping(value = "/equipe", method = {RequestMethod.GET, RequestMethod.POST})
	public String todasEquipes(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			EquipeDAO daoE = new EquipeHib();
			MaratonaDAO daoM = new MaratonaHib();
			
			List<MaratonaBean> listMaratona = new ArrayList<MaratonaBean>();
			for(Maratona e : daoM.list()) {
				MaratonaBean bean = new MaratonaBean();
				bean.toBean(e);
				listMaratona.add(bean);
			}
			
			List<EquipeBean> listEquipe = new ArrayList<EquipeBean>();
			for(Equipe e : daoE.list()) {
				EquipeBean bean = new EquipeBean();
				bean.toBean(e);
				listEquipe.add(bean);
			}
			
			model.addAttribute("maratonas", listMaratona);
			model.addAttribute("equipes", listEquipe);
			return "listar_equipes";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/equipe/{maratonaLis}/{casaLis}", method = {RequestMethod.GET, RequestMethod.POST})
	public String equipesPorMaratonaECasa(@PathVariable String maratonaLis, @PathVariable String casaLis, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			EquipeDAO daoE = new EquipeHib();
			MaratonaDAO daoM = new MaratonaHib();
			
			List<EquipeBean> list = new ArrayList<EquipeBean>();
			
			try {
				
				if(maratonaLis.toUpperCase().equals("ALL")) {
					
					if(casaLis.toUpperCase().equals("ALL")){
						for(Equipe e : daoE.list()) {
							EquipeBean bean = new EquipeBean();
							bean.toBean(e);
							list.add(bean);
						}
					}
					else {
						for(Equipe e : daoE.listByCasa(Casa.getCasa(casaLis))) {
							EquipeBean bean = new EquipeBean();
							bean.toBean(e);
							list.add(bean);
						}
					}
					
				} else {
					Maratona maratonaEntity = daoM.get(Long.parseLong(maratonaLis));
					
					if(casaLis.toUpperCase().equals("ALL")) {
						for(Equipe e : daoE.listByMaratona(maratonaEntity)) {
							EquipeBean bean = new EquipeBean();
							bean.toBean(e);
							list.add(bean);
						}
					} else {
						for(Equipe e : daoE.listByMaratonaCasa(maratonaEntity, Casa.getCasa(casaLis))) {
							EquipeBean bean = new EquipeBean();
							bean.toBean(e);
							list.add(bean);
						}
					}
				}
				
				
			} catch(Exception e) {
				model.addAttribute("msg","Maratona não encontrada");
			}
			
			List<MaratonaBean> listMaratona = new ArrayList<MaratonaBean>();
			for(Maratona e : daoM.list()) {
				MaratonaBean bean = new MaratonaBean();
				bean.toBean(e);
				listMaratona.add(bean);
			}
			
			model.addAttribute("maratonas", listMaratona);
			model.addAttribute("equipes", list);
			return "listar_equipes";
		}
		
		return "redirect:/";
	}
}
