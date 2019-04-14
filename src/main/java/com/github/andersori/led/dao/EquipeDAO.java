package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Usuario;

public interface EquipeDAO {
	
	public Equipe get(Long id);

    public Equipe get(Usuario user);

    public void add(Equipe eq);

    public void update(Equipe eq);

    public List<Equipe> list();

    public void remove(Equipe eq);
    
}
