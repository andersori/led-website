package com.github.andersori.led.dao.hibernate;

import java.util.List;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.MaratonaDAO;
import com.github.andersori.led.entity.Maratona;

public class MaratonaHib implements MaratonaDAO{

	private DAO<Maratona> dao;
	
	public MaratonaHib() {
		dao = new DAOHibernate<Maratona>();
	}
	@Override
	public Maratona get(Long id) {
		return dao.get(Maratona.class, id);
	}

	@Override
	public void add(Maratona mar) {
		dao.save(mar);
	}

	@Override
	public void update(Maratona mar) {
		dao.update(mar);
	}

	@Override
	public List<Maratona> list() {
		return dao.list(new Maratona());
	}

	@Override
	public void remove(Maratona mar) {
		dao.remove(mar);
	}

}
