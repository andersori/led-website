package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

@Controller("cadastrarEquipe")
@RequestMapping("/CadastrarEquipe")
public class CadastrarEquipe {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="nomeEquipe", required=false) String nome,
							@RequestParam(name="emailEquipe", required=false) String email,
							@RequestParam(name="usernameEquipe", required=false) String username,
							@RequestParam(name="senhaEquipe", required=false) String senha,
							@RequestParam(name="turmaEquipe", required=false) Long turma,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			TurmaDAO daoT = new TurmaHib();
			EquipeDAO daoE = new EquipeHib();
			
			if(nome != null && username != null && senha != null && turma != null) {
				Usuario usuarioEntity = new Usuario();
				usuarioEntity.setNome(nome);
				usuarioEntity.setPermissao(Permissao.EQUIPE);
				usuarioEntity.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
				usuarioEntity.setUsername(username);
				
				if(email != null) {
					usuarioEntity.setEmail(email);
				}
				
				try {				
					Equipe equipeEntity = new Equipe();
					equipeEntity.setCasa(Casa.INDEFINIDO);
					equipeEntity.setTurma(daoT.get(turma));
					equipeEntity.setUsuario(usuarioEntity);
				
					daoE.add(equipeEntity);
					model.addAttribute("msg", "Equipe '"+nome+"' cadastrado com sucesso.");
				
				} catch(Exception e) {
					e.printStackTrace();
					Throwable t = e.getCause();
				    while ((t != null) && !(t instanceof ConstraintViolationException)) {
				        t = t.getCause();
				    }
				    if (t instanceof ConstraintViolationException) {
				    	model.addAttribute("msg", "Não foi possivel cadastrar a equipe '"+nome+"'. Username já está em uso.");
				    }
				    else {
				    	model.addAttribute("msg", "Não foi possivel cadastrar a equipe '"+nome+"' por motivos misteriosos.");
				    }
				}
			}
			
			List<TurmaBean> listTurma = new ArrayList<>();
			List<EquipeBean> listEquipe = new ArrayList<>();
			
			for(Turma t : daoT.list()) {
				TurmaBean turmaB = new TurmaBean();
				turmaB.toBean(t);
				listTurma.add(turmaB);
			}
			for(Equipe e : daoE.list()) {
				EquipeBean equipeB = new EquipeBean();
				equipeB.toBean(e);
				listEquipe.add(equipeB);
			}
			
			model.addAttribute("turmas", listTurma);
			model.addAttribute("equipes", listEquipe);
			return "cadastrar_equipe";
		} else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage( @RequestParam(name="nomeEquipe", required=false) String nome,
							@RequestParam(name="emailEquipe", required=false) String email,
							@RequestParam(name="usernameEquipe", required=false) String username,
							@RequestParam(name="senhaEquipe", required=false) String senha,
							@RequestParam(name="turmaEquipe", required=false) Long turma,
							Model model, HttpServletRequest request) {
		
		return getPage(nome, email, username, senha, turma, model, request);
	}
}
