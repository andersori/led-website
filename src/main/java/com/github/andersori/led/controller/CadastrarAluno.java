package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.AlunoBean;
import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.dao.AlunoDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.hibernate.AlunoHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.entity.Aluno;
import com.github.andersori.led.entity.Turma;

@Controller("cadastrarAluno")
@RequestMapping("/CadastrarAluno")
public class CadastrarAluno {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="nomeAluno", required=false) String nome,
							@RequestParam(name="matriculaAluno", required=false) String matricula,
							@RequestParam(name="turmaAluno", required=false) Long turma,
							Model model, HttpServletRequest request) {
		
		TurmaDAO daoT = new TurmaHib();
		AlunoDAO daoM = new AlunoHib();
		
		if(nome != null && matricula != null && turma != null) {
			Aluno alunoEntity = new Aluno();
			alunoEntity.setMatricula(matricula);
			alunoEntity.setNome(nome);
			alunoEntity.setTurma(daoT.get(turma));
			
			try {
				daoM.add(alunoEntity);
				model.addAttribute("msg", "Aluno '"+nome+"' cadastrado com sucesso.");
			} catch (Exception e) {
				model.addAttribute("msg", "Não foi possivel cadastrar o(a) aluno(a) '"+nome+"' por motivos misteriosos.");
			}
		}
		
		List<TurmaBean> listTurma = new ArrayList<>();
		List<AlunoBean> listMembro = new ArrayList<>();
		
		for(Aluno m : daoM.list()) {
			AlunoBean alunoB = new AlunoBean();
			alunoB.toBean(m);
			listMembro.add(alunoB);
		}
		for(Turma t : daoT.list()) {
			TurmaBean turmaB = new TurmaBean();
			turmaB.toBean(t);
			listTurma.add(turmaB);
		}
		
		model.addAttribute("alunos", listMembro);
		model.addAttribute("turmas", listTurma);
		return "cadastrar_membro";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage(	@RequestParam(name="nomeAluno", required=false) String nome,
							@RequestParam(name="matriculaAluno", required=false) String matricula,
							@RequestParam(name="turmaAluno", required=false) Long turma,
							Model model, HttpServletRequest request) {
		
		return getPage(nome, matricula, turma, model, request);
	}
}
