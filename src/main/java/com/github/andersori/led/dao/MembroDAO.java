package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Membro;

public interface MembroDAO {
	
	public Membro get(Long id);

    public void add(Membro mem);

    public void update(Membro mem);

    public List<Membro> list();

    public void remove(Membro mem);
    
}
