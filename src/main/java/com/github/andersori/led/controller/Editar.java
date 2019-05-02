package com.github.andersori.led.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.MaratonaBean;
import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.MaratonaDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.dao.hibernate.MaratonaHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

@Controller("editar")
@RequestMapping("/editar")
public class Editar {
	
	@RequestMapping(value = "/equipe/{idEquipe}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarEquipe(	@PathVariable Long idEquipe,
								@RequestParam(name="nomeEquipe", required=false) String nome,
								@RequestParam(name="emailEquipe", required=false) String email,
								@RequestParam(name="usernameEquipe", required=false) String username,
								@RequestParam(name="senhaEquipe", required=false) String senha,
								@RequestParam(name="turmaEquipe", required=false) Long turma,
								@RequestParam(name="maratonaEquipe", required=false) Long maratona,
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		TurmaDAO daoT = new TurmaHib();
		EquipeDAO daoE = new EquipeHib();
		MaratonaDAO daoM = new MaratonaHib();
		
		if(user.getPermissao() == Permissao.ADM) {
			
			Equipe equipeEntity = daoE.get(idEquipe);
			
			if(nome != null && username != null && senha != null && turma != null && maratona != null) {

				if(equipeEntity != null){
					
					Usuario usuarioEntity = equipeEntity.getUsuario();
					usuarioEntity.setNome(nome);
					usuarioEntity.setPermissao(Permissao.EQUIPE);
					
					if(!senha.isEmpty()) {
						usuarioEntity.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
					}
					
					if(!username.equals(usuarioEntity.getUsername()))
					{
						usuarioEntity.setUsername(username);
					}
					
					if(email != null) {
						usuarioEntity.setEmail(email);
					}
					
					equipeEntity.setUsuario(usuarioEntity);
					
					Turma turmaEntity = daoT.get(turma);
					if(turmaEntity != null)
					{
						equipeEntity.setTurma(turmaEntity);
					}
					
					Maratona maratonaEntity = daoM.get(maratona);
					if(maratonaEntity != null)
					{
						equipeEntity.setMaratona(maratonaEntity);
					}
					
					try {				
						daoE.update(equipeEntity);
						model.addAttribute("msg", "Equipe '"+nome+"' editada com sucesso.");
					
					} catch(Exception e) {
						Throwable t = e.getCause();
					    while ((t != null) && !(t instanceof ConstraintViolationException)) {
					        t = t.getCause();
					    }
					    if (t instanceof ConstraintViolationException) {
					    	model.addAttribute("msg", "Não foi possivel editar a equipe '"+nome+"'. Username já está em uso.");
					    }
					    else {
					    	model.addAttribute("msg", "Não foi possivel editar a equipe '"+nome+"' por motivos misteriosos.");
					    }
					}
				} else {
					model.addAttribute("msg", "Turma não encontrada.");
				}

			}
			
			if(equipeEntity != null)
			{
				model.addAttribute("nome", equipeEntity.getUsuario().getNome());
				model.addAttribute("username", equipeEntity.getUsuario().getUsername());
				model.addAttribute("email", equipeEntity.getUsuario().getEmail());
				model.addAttribute("id_turma", equipeEntity.getTurma().getId());
				
				if(equipeEntity.getMaratona() != null)
				{
					model.addAttribute("id_maratona", equipeEntity.getMaratona().getId());
				}
				
				model.addAttribute("id_equipe", equipeEntity.getId());
			}
			
			List<TurmaBean> listTurma = new ArrayList<>();
			List<EquipeBean> listEquipe = new ArrayList<>();
			List<MaratonaBean> listMaratona = new ArrayList<>();
			
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
			for(Maratona m : daoM.list()) {
				MaratonaBean maratonaB = new MaratonaBean();
				maratonaB.toBean(m);
				listMaratona.add(maratonaB);
			}
			
			model.addAttribute("turmas", listTurma);
			model.addAttribute("equipes", listEquipe);
			model.addAttribute("maratonas", listMaratona);
			return "editar_equipe";
			
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarUsuario(@PathVariable Long idUsuario, 
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			
		} else {
			return "redirect:/";
		}
		
		return "editar_usuario";
	}
	
	@RequestMapping(value = "/aluno/{idUsuario}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarAluno(@PathVariable Long idUsuario, 
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			
		} else {
			return "redirect:/";
		}
		
		return "editar_aluno";
	}
	
	@RequestMapping(value = "/maratona/{idUsuario}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarMaratona(@PathVariable Long idUsuario, 
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			
		} else {
			return "redirect:/";
		}
		
		return "editar_maratona";
	}
}
