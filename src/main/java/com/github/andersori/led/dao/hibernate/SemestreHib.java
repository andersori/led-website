package com.github.andersori.led.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.andersori.led.config.Hibernate;
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

	@Override
	@SuppressWarnings("unchecked")
	public boolean semestreJaCadastrado(Semestre semestre) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long cont = 0;

        try {
            Query<Long> qry = session.getNamedQuery("semestre_ja_existe");
            qry.setParameter("ano", semestre.getAno());
            qry.setParameter("numSemestre", semestre.getNumSemestre());

            cont = ((Long) qry.getSingleResult()).intValue();
            t.commit();
            
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }

        if(cont >= 1){
            return true;
        } else {
            return false;
        }
	}

}
