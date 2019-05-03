package com.github.andersori.led.bean;

import com.github.andersori.led.entity.Permissao;
import com.github.andersori.led.entity.Usuario;

public class UsuarioBean implements Bean<Usuario>{
	
	private Long id;
    private String username;
    private String nome;
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

	@Override
	public void toBean(Usuario entity) {
		if(entity != null) {
			setId(entity.getId());
			setNome(entity.getNome());
			setSenha(entity.getSenha());
			setToken(entity.getToken());
			setPermissao(entity.getPermissao());
			setUsername(entity.getUsername());
		} 
		/*else {
			throw new NullPointerException("Entidade Usuario nula na converção para bean.");
		}*/
	}

	@Override
	public Usuario toEntity() {
		Usuario entity = new Usuario();
		entity.setId(getId());
		entity.setNome(getNome());
		entity.setSenha(getSenha());
		entity.setToken(getToken());
		entity.setPermissao(getPermissao());
		entity.setUsername(getUsername());
		return entity;
	}
}
