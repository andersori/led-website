package com.github.andersori.led.dao.hibernate;

import java.util.List;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.AlunoDAO;
import com.github.andersori.led.entity.Aluno;

public class AlunoHib implements AlunoDAO{
	
	private DAO<Aluno> dao;

    public AlunoHib(){
        this.dao = new DAOHibernate<>();
    }
    
    @Override
    public Aluno get(Long id) {
        return dao.get(Aluno.class, id);
    }

    @Override
    public void add(Aluno mem) {
        dao.save(mem);
    }

    @Override
    public void update(Aluno mem) {
        dao.update(mem);
    }

    @Override
    public List<Aluno> list() {
        return dao.list(new Aluno());
    }

    @Override
    public void remove(Aluno mem) {
        dao.remove(mem);
    }
    
}
