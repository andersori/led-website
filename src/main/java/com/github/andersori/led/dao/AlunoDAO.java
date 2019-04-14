package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Aluno;

public interface AlunoDAO {
	
	public Aluno get(Long id);

    public void add(Aluno mem);

    public void update(Aluno mem);

    public List<Aluno> list();

    public void remove(Aluno mem);
    
}
