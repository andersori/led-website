package com.github.andersori.led.controller;

import java.time.LocalDate;
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

import com.github.andersori.led.bean.AlunoBean;
import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.SemestreBean;
import com.github.andersori.led.bean.TurmaBean;
import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.AlunoDAO;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.dao.MaratonaDAO;
import com.github.andersori.led.dao.SemestreDAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.AlunoHib;
import com.github.andersori.led.dao.hibernate.EquipeHib;
import com.github.andersori.led.dao.hibernate.MaratonaHib;
import com.github.andersori.led.dao.hibernate.SemestreHib;
import com.github.andersori.led.dao.hibernate.TurmaHib;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Aluno;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Semestre;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

@Controller("cadastrar")
@RequestMapping("/cadastrar")
public class Cadastrar {
	
	@RequestMapping(value = "/aluno", method = {RequestMethod.GET, RequestMethod.POST})
	public String aluno(@RequestParam(name="nomeAluno", required=false) String nome,
						@RequestParam(name="matriculaAluno", required=false) String matricula,
						@RequestParam(name="turmaAluno", required=false) Long turma,
						Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
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
			
			return "cadastrar_aluno";
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}
	
	@RequestMapping(value = "/equipe", method = {RequestMethod.GET, RequestMethod.POST})
	public String equipe(	@RequestParam(name="nomeEquipe", required=false) String nome,
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
				
				try {				
					Equipe equipeEntity = new Equipe();
					equipeEntity.setCasa(Casa.INDEFINIDO);
					equipeEntity.setTurma(daoT.get(turma));
					equipeEntity.setUsuario(usuarioEntity);
				
					daoE.add(equipeEntity);
					model.addAttribute("msg", "Equipe '"+nome+"' cadastrado com sucesso.");
				
				} catch(Exception e) {
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
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}
	
	@RequestMapping(value = "/maratona", method = {RequestMethod.GET, RequestMethod.POST})
	public String maratona(	@RequestParam(name="semestreMaratona", required=false) Long semestre,
							@RequestParam(name="dataMaratona", required=false) String data,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			SemestreDAO daoS = new SemestreHib();
			
			if(semestre != null && data != null) {
				MaratonaDAO daoM = new MaratonaHib();
				
				Maratona maratona = new Maratona();
				maratona.setData(LocalDate.parse(data));
				maratona.setSemestre(daoS.get(semestre));
				
				try {
					daoM.add(maratona);
					model.addAttribute("msg", "Maratona cadastrada com sucesso.");
				} catch(Exception e) {
					model.addAttribute("msg", "Não foi possível cadastrar a maratona.");
				}
				
			}
			
			
			List<SemestreBean> listSemestre = new ArrayList<SemestreBean>();
			for(Semestre s : daoS.list()) {
				SemestreBean sBean = new SemestreBean();
				sBean.toBean(s);
				listSemestre.add(sBean);
			}
			
			model.addAttribute("semestres", listSemestre);
			return "cadastrar_maratona";
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}

	@RequestMapping(value = "/semestre", method = {RequestMethod.GET, RequestMethod.POST})
	public String semestre(	@RequestParam(name="anoSemestre", required=false) Integer ano,
							@RequestParam(name="numSemestre", required=false) Integer semestre,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
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
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}
	
	@RequestMapping(value = "/turma", method = {RequestMethod.GET, RequestMethod.POST})
	public String turma(@RequestParam(name="nomeTurma", required=false) String nome,
						@RequestParam(name="codTurma", required=false) String cod,
						@RequestParam(name="cursoTurma", required=false) String curso,
						@RequestParam(name="semestreTurma", required=false) Long semestre,
						Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
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
					model.addAttribute("msg", "A turma '" +nome+ "' não pode ser cadastrada por motivos desconhecidos.");
				}
				
			}
			
			List<TurmaBean> listTurma = new ArrayList<TurmaBean>();
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
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}
	
	@RequestMapping(value = "/usuario", method = {RequestMethod.GET, RequestMethod.POST})
	public String usuario(	@RequestParam(name="nomeUsuario", required=false) String nome,
							@RequestParam(name="usernameUsuario", required=false) String username,
							@RequestParam(name="senhaUsuario", required=false) String senha,
							Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			if(nome != null && username != null && senha != null) {
				Usuario usuarioEntity = new Usuario();
				usuarioEntity.setNome(nome);
				usuarioEntity.setPermissao(Permissao.ADM);
				usuarioEntity.setUsername(username);
				usuarioEntity.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
				
				UsuarioDAO dao = new UsuarioHib();
				
				try {
					dao.add(usuarioEntity);
					
					model.addAttribute("msg", "Usuario '"+nome+"' cadastrado com sucesso.");
				
				} catch(Exception e) {
					Throwable t = e.getCause();
				    while ((t != null) && !(t instanceof ConstraintViolationException)) {
				        t = t.getCause();
				    }
				    if (t instanceof ConstraintViolationException) {
				    	model.addAttribute("msg", "Não foi possivel cadastrar o usuario '"+nome+"'. Username já está em uso.");
				    }
				    else {
				    	model.addAttribute("msg", "Não foi possivel cadastrar o usuario '"+nome+"' por motivos misteriosos.");
				    }
				}
				
				model.addAttribute("nome", nome);
				model.addAttribute("username", username);
				model.addAttribute("senha", senha);
			}
			
			return "cadastrar_usuario";
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
	}
	
}
