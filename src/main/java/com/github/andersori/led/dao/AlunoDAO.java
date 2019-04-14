package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Aluno;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Turma;

public interface AlunoDAO {
	
	public Aluno get(Long id);

    public void add(Aluno mem);

    public void update(Aluno mem);

    public List<Aluno> list();
    
    public List<Aluno> listByTurma(Turma turma);
    
    public List<Aluno> listByEquipe(Equipe equipe);

    public void remove(Aluno mem);
    
}
