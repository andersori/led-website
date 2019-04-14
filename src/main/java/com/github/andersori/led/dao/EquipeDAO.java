package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Usuario;

public interface EquipeDAO {
	
	public Equipe get(Long id);

    public Equipe get(Usuario user);
    
    public List<Equipe> listByMaratona(Maratona maratona);
    
    public List<Equipe> listByMaratonaCasa(Maratona maratona, Casa casa);

    public void add(Equipe eq);

    public void update(Equipe eq);

    public List<Equipe> list();

    public void remove(Equipe eq);
    
}
