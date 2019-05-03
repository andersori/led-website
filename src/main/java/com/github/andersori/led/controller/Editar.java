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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.bean.EquipeBean;
import com.github.andersori.led.bean.MaratonaBean;
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
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Semestre;
import com.github.andersori.led.entity.Turma;
import com.github.andersori.led.entity.Usuario;

@Controller("editar")
@RequestMapping("/editar")
public class Editar {
	
	@RequestMapping(value = "/equipe/{idEquipe}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarEquipe(	@PathVariable Long idEquipe,
								@RequestParam(name="nomeEquipe", required=false) String nome,
								@RequestParam(name="usernameEquipe", required=false) String username,
								@RequestParam(name="pontosEquipe", required=false) Integer pontos,
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
			
			if(nome != null && username != null && senha != null && turma != null && maratona != null && pontos != null) {

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
					
					equipeEntity.setUsuario(usuarioEntity);
					equipeEntity.setPontos(pontos);
					
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
				model.addAttribute("id_turma", equipeEntity.getTurma().getId());
				model.addAttribute("pontos", equipeEntity.getPontos());
				
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
			return "redirect:" + request.getContextPath() + "/";
		}
	}
	
	@RequestMapping(value = "/usuario/{idUsuario}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarUsuario(@PathVariable Long idUsuario,
								@RequestParam(name="nomeUsuario", required=false) String nome,
								@RequestParam(name="usernameUsuario", required=false) String username,
								@RequestParam(name="senhaUsuario", required=false) String senha,
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			UsuarioDAO dao = new UsuarioHib();
			Usuario usuarioEntity = dao.get(idUsuario);
			
			if(usuarioEntity != null){
				
				if(nome != null && username != null && senha != null) {
					
					usuarioEntity.setNome(nome);
					usuarioEntity.setPermissao(Permissao.ADM);
					usuarioEntity.setUsername(username);
					
					if(!senha.isEmpty()){
						usuarioEntity.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
					}
					
					try {
						dao.update(usuarioEntity);
						
						model.addAttribute("msg", "Usuario '"+nome+"' editado com sucesso.");
					
					} catch(Exception e) {
						Throwable t = e.getCause();
					    while ((t != null) && !(t instanceof ConstraintViolationException)) {
					        t = t.getCause();
					    }
					    if (t instanceof ConstraintViolationException) {
					    	model.addAttribute("msg", "Não foi possivel editar o usuario '"+nome+"'. Username já está em uso.");
					    }
					    else {
					    	model.addAttribute("msg", "Não foi possivel editar o usuario '"+nome+"' por motivos misteriosos.");
					    }
					}
				}
				
				model.addAttribute("nome", usuarioEntity.getNome());
				model.addAttribute("username", usuarioEntity.getUsername());
				model.addAttribute("id_usuario", usuarioEntity.getId());
			}
			
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
		return "editar_usuario";
	}
	
	@RequestMapping(value = "/aluno/{idAluno}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarAluno(	@PathVariable Long idAluno,
								@RequestParam(name="nomeAluno", required=false) String nome,
								@RequestParam(name="matriculaAluno", required=false) String matricula,
								@RequestParam(name="turmaAluno", required=false) Long turma,
								@RequestParam(name="equipeAluno", required=false) Long equipe,
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			
			TurmaDAO daoT = new TurmaHib();
			AlunoDAO daoA = new AlunoHib();
			EquipeDAO daoE = new EquipeHib();
			
			Aluno alunoEntity = daoA.get(idAluno);
			
			if(nome != null && matricula != null && turma != null && equipe != null) {
				
				if(alunoEntity != null){
					alunoEntity.setMatricula(matricula);
					alunoEntity.setNome(nome);
					
					Turma turmaEntity = daoT.get(turma);
					if(turmaEntity != null) {
						alunoEntity.setTurma(turmaEntity);
					}
					
					if(equipe != -1)
					{
						Equipe equipeEntity = daoE.get(equipe);
						if(equipeEntity != null)
						{
							alunoEntity.setEquipe(equipeEntity);
						}						
					}
					
					try {
						daoA.update(alunoEntity);
						model.addAttribute("msg", "Aluno(a) '"+nome+"' atualizado(a) com sucesso.");
					} catch (Exception e) {
						model.addAttribute("msg", "Não foi possivel atualizar o(a) aluno(a) '"+nome+"' por motivos misteriosos.");
					}
				} else {
					model.addAttribute("msg", "Aluno(a) não encontrado(a).");
				}
				
			}
			
			
			if(alunoEntity != null){
				model.addAttribute("nomeAluno", alunoEntity.getNome());
				model.addAttribute("matriculaAluno", alunoEntity.getMatricula());
				model.addAttribute("id_turma", alunoEntity.getTurma().getId());
				model.addAttribute("id_aluno", alunoEntity.getId());
				
				if(alunoEntity.getEquipe() != null)
				{
					model.addAttribute("id_equipe", alunoEntity.getEquipe().getId());
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
			
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
		return "editar_aluno";
	}
	
	@RequestMapping(value = "/maratona/{idMaratona}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarMaratona(	@PathVariable Long idMaratona,
									@RequestParam(name="semestreMaratona", required=false) Long semestre,
									@RequestParam(name="dataMaratona", required=false) String data,
									Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			SemestreDAO daoS = new SemestreHib();
			MaratonaDAO daoM = new MaratonaHib();
			
			Maratona maratona = daoM.get(idMaratona);
			
			if(semestre != null && data != null) {
				
				if(maratona != null) {
					maratona.setData(LocalDate.parse(data));
					maratona.setSemestre(daoS.get(semestre));
					
					try {
						daoM.update(maratona);
						model.addAttribute("msg", "Maratona editada com sucesso.");
					} catch(Exception e) {
						model.addAttribute("msg", "Não foi possível editar a maratona.");
					}
				} else {
					model.addAttribute("msg", "A equipe não foi encontrada.");
				}
				
			}
			
			if(maratona != null){
				model.addAttribute("id_maratona", maratona.getId());
				model.addAttribute("id_semestre", maratona.getSemestre().getId());
				model.addAttribute("dataMaratona", maratona.getData().toString());
			}
			
			List<SemestreBean> listSemestre = new ArrayList<SemestreBean>();
			for(Semestre s : daoS.list()) {
				SemestreBean sBean = new SemestreBean();
				sBean.toBean(s);
				listSemestre.add(sBean);
			}
			
			model.addAttribute("semestres", listSemestre);
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
		return "editar_maratona";
	}
	
	@RequestMapping(value = "/turma/{idTurma}", method = {RequestMethod.GET, RequestMethod.POST})
	public String editarTurma(	@PathVariable Long idTurma,
								@RequestParam(name="nomeTurma", required=false) String nome,
								@RequestParam(name="codTurma", required=false) String cod,
								@RequestParam(name="cursoTurma", required=false) String curso,
								@RequestParam(name="semestreTurma", required=false) Long semestre,
								Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		
		if(user.getPermissao() == Permissao.ADM) {
			TurmaDAO daoT = new TurmaHib();
			SemestreDAO daoS = new SemestreHib();
			
			Turma turmaEntity = daoT.get(idTurma);
			
			if(turmaEntity != null) {
				if(nome != null && cod != null && curso != null && semestre != null) {
					turmaEntity.setCodDisciplina(cod);
					turmaEntity.setCurso(curso);
					turmaEntity.setNome(nome);
					turmaEntity.setSemestre(daoS.get(semestre));
					
					try {
						daoT.update(turmaEntity);
						model.addAttribute("msg", "Turma editada com sucesso.");
					} catch(Exception e) {
						model.addAttribute("msg", "A turma '" +nome+ "' não pode ser editada por motivos desconhecidos.");
					}
				}
				model.addAttribute("nome", turmaEntity.getNome());
				model.addAttribute("cod", turmaEntity.getCodDisciplina());
				model.addAttribute("curso", turmaEntity.getCurso());
				model.addAttribute("id_semestre", turmaEntity.getSemestre().getId());
				model.addAttribute("id_turma", turmaEntity.getId());
				
				List<SemestreBean> listSemestre = new ArrayList<SemestreBean>();
				for(Semestre s : daoS.list()) {
					SemestreBean sBean = new SemestreBean();
					sBean.toBean(s);
					listSemestre.add(sBean);
				}
				
				model.addAttribute("semestres", listSemestre);
			} else {
				model.addAttribute("mds", "Turma não encontrada.");
			}
			
			
		} else {
			return "redirect:" + request.getContextPath() + "/";
		}
		
		return "editar_turma";
	}
}
