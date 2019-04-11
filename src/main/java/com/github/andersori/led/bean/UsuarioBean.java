package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Usuario;

public class UsuarioBean {
	
	private Long id;
    private String username;
    private String nome;
    private String email;
    private String senha;
    private Permissao permissao;
    private String token;
    
    public UsuarioBean() {
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static UsuarioBean toBean(Usuario entity) {
		if(entity != null) {
			UsuarioBean bean = new UsuarioBean();
			bean.setId(entity.getId());
			bean.setNome(entity.getNome());
			bean.setSenha(entity.getSenha());
			bean.setToken(entity.getToken());
			bean.setEmail(entity.getEmail());
			bean.setPermissao(entity.getPermissao());
			bean.setUsername(entity.getUsername());
			return bean;
		}
		return null;
	}

	public static Usuario toEntity(UsuarioBean bean) {
		if(bean != null) {
			Usuario entity = new Usuario();
			entity.setId(bean.getId());
			entity.setNome(bean.getNome());
			entity.setSenha(bean.getSenha());
			entity.setToken(bean.getToken());
			entity.setEmail(bean.getEmail());
			entity.setPermissao(bean.getPermissao());
			entity.setUsername(bean.getUsername());
			return entity;
		}
		return null;
	}
}
