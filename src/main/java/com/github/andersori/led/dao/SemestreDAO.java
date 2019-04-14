package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Semestre;

public interface SemestreDAO{
	
	public Semestre get(Long id);

    public void add(Semestre sem);

    public void update(Semestre sem);

    public List<Semestre> list();
    
    public boolean semestreJaCadastrado(Semestre semestre);

    public void remove(Semestre sem);
}
