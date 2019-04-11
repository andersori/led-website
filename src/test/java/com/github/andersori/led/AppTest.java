package com.github.andersori.led;

import org.mindrot.jbcrypt.BCrypt;

import com.github.andersori.led.bean.UsuarioBean;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.dao.hibernate.UsuarioHib;
import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Usuario;
import com.github.andersori.led.util.Util;

public class AppTest{
    
	public static void main(String[] args) {
		usuario();
	}
	
	public static void usuario() {
		UsuarioBean u = new UsuarioBean();
		u.setNome("Usuario da silva");
		u.setUsername("user");
		u.setSenha(BCrypt.hashpw("1234", BCrypt.gensalt()));
		u.setPermissao(Permissao.ADM);
		
		Usuario uData = UsuarioBean.toEntity(u);
		
		UsuarioDAO dao = new UsuarioHib();
		dao.add(uData);
		
		System.out.println("--------------Pegando o usuario--------------");
		System.out.println(Util.toString(dao.get("user")));
	}
	
	public static void membro() {
			
	}
	
	public static void turma() {
		
	}
	
	public static void equipe() {
		
	}


}
