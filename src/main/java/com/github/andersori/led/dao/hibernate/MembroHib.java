package com.github.andersori.led.dao.hibernate;

import java.util.List;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.MembroDAO;
import com.github.andersori.led.entity.Membro;

public class MembroHib implements MembroDAO{
	
	private DAO<Membro> dao;

    public MembroHib(){
        this.dao = new DAOHibernate<>();
    }
    
    @Override
    public Membro get(Long id) {
        return dao.get(Membro.class, id);
    }

    @Override
    public void add(Membro mem) {
        dao.save(mem);
    }

    @Override
    public void update(Membro mem) {
        dao.update(mem);
    }

    @Override
    public List<Membro> list() {
        return dao.list(new Membro());
    }

    @Override
    public void remove(Membro mem) {
        dao.remove(mem);
    }
    
}
