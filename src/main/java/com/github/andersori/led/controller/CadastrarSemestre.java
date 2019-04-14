package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.SemestreBean;
import com.github.andersori.led.dao.SemestreDAO;
import com.github.andersori.led.dao.hibernate.SemestreHib;
import com.github.andersori.led.entity.Semestre;

@Controller("cadastrarSemestre")
@RequestMapping("/CadastrarSemestre")
public class CadastrarSemestre {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="anoSemestre", required=false) Integer ano,
							@RequestParam(name="numSemestre", required=false) Integer semestre,
							Model model, HttpServletRequest request) {
		
		SemestreDAO daoS = new SemestreHib();
		
		if(ano != null && semestre != null) {
			Semestre semestreEntity = new Semestre();
			semestreEntity.setAno(ano);
			semestreEntity.setNumSemestre(semestre);
			
			try {
				if(!daoS.semestreJaCadastrado(semestreEntity)) {
					daoS.add(semestreEntity);
					model.addAttribute("msg", "Semestre cadastrado com sucesso.");
				} else {
					model.addAttribute("msg", "Este Semestre ja esta cadastrado.");
				}
				
			} catch(Exception e) {
				model.addAttribute("msg", "O semestre '" +ano+"."+semestre+ "' não pode ser cadastrado por motivos desconhecidos.");
			}
			
			
		}
		
		List<SemestreBean> listSemestre = new ArrayList<SemestreBean>();
		for(Semestre s : daoS.list()) {
			SemestreBean beanSemestre = new SemestreBean();
			beanSemestre.toBean(s);
			listSemestre.add(beanSemestre);
		}
		
		model.addAttribute("semestres", listSemestre);
		return "cadastrar_semestre";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage( @RequestParam(name="anoSemestre", required=false) Integer ano,
							@RequestParam(name="numSemestre", required=false) Integer semestre,
							Model model, HttpServletRequest request) {
		
		return getPage(ano, semestre, model, request);
	}
}
