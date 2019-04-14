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
import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.dao.SemestreDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.hibernate.SemestreHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.entity.Semestre;
import com.github.andersori.led.entity.Turma;

@Controller("cadastrarTurma")
@RequestMapping("/CadastrarTurma")
public class CadastrarTurma {

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="nomeTurma", required=false) String nome,
							@RequestParam(name="codTurma", required=false) String cod,
							@RequestParam(name="cursoTurma", required=false) String curso,
							@RequestParam(name="semestreTurma", required=false) Long semestre,
							Model model, HttpServletRequest request) {
		
		TurmaDAO dao = new TurmaHib();
		SemestreDAO daoS = new SemestreHib();
		
		if(nome != null && cod != null && curso != null) {
			Turma turmaEntity = new Turma();
			turmaEntity.setCodDisciplina(cod);
			turmaEntity.setCurso(curso);
			turmaEntity.setNome(nome);
			
			
			turmaEntity.setSemestre(daoS.get(semestre));
			
			try {
				dao.add(turmaEntity);
				model.addAttribute("msg", "Turma Cadastrada com sucesso.");
			} catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "A turma '" +nome+ "' não pode ser cadastrada por motivos desconhecidos.");
			}
			
		}
		
		List<TurmaBean> listTurma = new ArrayList<>();
		for(Turma t : dao.list()) {
			TurmaBean turBean = new TurmaBean();
			turBean.toBean(t);
			listTurma.add(turBean);
		}
		
		List<SemestreBean> listSemestre = new ArrayList<SemestreBean>();
		for(Semestre s : daoS.list()) {
			SemestreBean sBean = new SemestreBean();
			sBean.toBean(s);
			listSemestre.add(sBean);
		}
		
		model.addAttribute("turmas", listTurma);
		model.addAttribute("semestres", listSemestre);
		return "cadastrar_turma";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage( @RequestParam(name="nomeTurma", required=false) String nome,
							@RequestParam(name="codTurma", required=false) String cod,
							@RequestParam(name="cursoTurma", required=false) String curso,
							@RequestParam(name="semestreTurma", required=false) Long semestre,
							Model model, HttpServletRequest request) {
		return getPage(nome, cod, curso, semestre, model, request);
	}
}
