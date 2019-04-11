package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Usuario;

public interface UsuarioDAO {
	
    public Usuario get(Long id);

    public Usuario get(String username);

    public void add(Usuario us);

    public void update(Usuario us);

    public List<Usuario> list();

    public void remove(Usuario us);

    public boolean validar(String username, String senha);
	
}
