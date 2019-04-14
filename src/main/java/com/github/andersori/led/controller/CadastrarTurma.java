package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.entity.Turma;

@Controller("cadastrarTurma")
@RequestMapping("/CadastrarTurma")
public class CadastrarTurma {

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="nomeTurma", required=false) String nome,
							@RequestParam(name="codTurma", required=false) String cod,
							@RequestParam(name="cursoTurma", required=false) String curso,
							Model model, HttpServletRequest request) {
		
		TurmaDAO dao = new TurmaHib();
		
		if(nome != null && cod != null && curso != null) {
			TurmaBean bean = new TurmaBean();
			bean.setCodDisciplina(cod);
			bean.setCurso(curso);
			bean.setNome(nome);
			
			try {
				dao.add(bean.toEntity());
			} catch(Exception e) {
				model.addAttribute("msg", "A turma '" +nome+ "' não pode ser cadastrada por motivos desconhecidos.");
			}
			
			model.addAttribute("msg", "Turma Cadastrada com sucesso.");
			
		}
		
		List<TurmaBean> list = new ArrayList<>();
		for(Turma t : dao.list()) {
			TurmaBean turBean = new TurmaBean();
			turBean.toBean(t);
			list.add(turBean);
		}
		
		model.addAttribute("turmas", list);
		return "cadastrar_turma";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage( @RequestParam(name="nomeTurma", required=false) String nome,
							@RequestParam(name="codTurma", required=false) String cod,
							@RequestParam(name="cursoTurma", required=false) String curso,
							Model model, HttpServletRequest request) {
		return getPage(nome, cod, curso, model, request);
	}
}
