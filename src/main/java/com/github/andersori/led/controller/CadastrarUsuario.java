package com.github.andersori.led.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Usuario;

@Controller("cadastrarUsuario")
@RequestMapping("/CadastrarUsuario")
public class CadastrarUsuario {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(	@RequestParam(name="nomeUsuario", required=false) String nome,
							@RequestParam(name="usernameUsuario", required=false) String username,
							@RequestParam(name="senhaUsuario", required=false) String senha,
							@RequestParam(name="emailUsuario", required=false) String email,
							Model model, HttpServletRequest request) {
		
		if(nome != null && username != null && senha != null) {
			Usuario usuarioEntity = new Usuario();
			usuarioEntity.setNome(nome);
			usuarioEntity.setPermissao(Permissao.ADM);
			usuarioEntity.setUsername(username);
			usuarioEntity.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
			
			if(email != null) {
				usuarioEntity.setEmail(email);
			}
			
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
			model.addAttribute("email", email);
		}
		
		return "cadastrar_usuario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postPage( @RequestParam(name="nomeUsuario", required=false) String nome,
							@RequestParam(name="usernameUsuario", required=false) String username,
							@RequestParam(name="senhaUsuario", required=false) String senha,
							@RequestParam(name="emailUsuario", required=false) String email,
							Model model, HttpServletRequest request) {
		
		return getPage(nome, username, senha, email, model, request);
	}
}
