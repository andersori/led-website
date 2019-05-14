package com.github.andersori.led.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.TurmaDAO;
import com.github.andersori.led.entity.Semestre;
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Turma> listTurmaBySemestre(Semestre semestre) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Turma> lis = new ArrayList<Turma>();

        try {
            Query<Turma> qry = session.getNamedQuery("get_turma_by_semestre");
            qry.setParameter("semestre", semestre);
            
            lis = qry.getResultList();
            t.commit();
            
        } catch (RuntimeException ex) {
            t.rollback();
            throw ex;
        } finally {
            session.close();
        }
        return lis;
	}
    
}
