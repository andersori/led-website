package com.github.andersori.led.dao.hibernate;

import java.util.List;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.entity.Turma;

public class TurmaHib implements TurmaDAO{
	
	private DAO<Turma> dao;

    public TurmaHib(){
        dao = new DAOHibernate<>();
    }
    
    @Override
    public Turma get(Long id) {
        return dao.get(Turma.class, id);
    }

    @Override
    public void add(Turma tur) {
        dao.save(tur);
    }

    @Override
    public void update(Turma tur) {
        dao.update(tur);
    }

    @Override
    public List<Turma> list() {
        return dao.list(new Turma());
    }

    @Override
    public void remove(Turma tur) {
        dao.remove(tur);
    }
    
}
