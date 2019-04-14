package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Maratona;

public interface MaratonaDAO {
	
	public Maratona get(Long id);

    public void add(Maratona mar);

    public void update(Maratona mar);

    public List<Maratona> list();

    public void remove(Maratona mar);
}
