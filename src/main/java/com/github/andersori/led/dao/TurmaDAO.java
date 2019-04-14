package com.github.andersori.led.dao;

import java.util.List;

import com.github.andersori.led.entity.Semestre;
import com.github.andersori.led.entity.Turma;

public interface TurmaDAO {

	public Turma get(Long id);

    public void add(Turma tur);

    public void update(Turma tur);

    public List<Turma> list();

    public void remove(Turma tur);
    
    public List<Turma> listTurmaBySemestre(Semestre semestre);
}
