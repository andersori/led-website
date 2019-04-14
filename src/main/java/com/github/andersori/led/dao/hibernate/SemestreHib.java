package com.github.andersori.led.dao.hibernate;

import java.util.List;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.SemestreDAO;
import com.github.andersori.led.entity.Semestre;

public class SemestreHib implements SemestreDAO{

	private DAO<Semestre> dao;
	
	public SemestreHib() {
		dao = new DAOHibernate<Semestre>();
	}
	
	@Override
	public Semestre get(Long id) {
		return dao.get(Semestre.class, id);
	}

	@Override
	public void add(Semestre sem) {
		dao.save(sem);
	}

	@Override
	public void update(Semestre sem) {
		dao.update(sem);
	}

	@Override
	public List<Semestre> list() {
		return dao.list(new Semestre());
	}

	@Override
	public void remove(Semestre sem) {
		dao.remove(sem);
	}

}
