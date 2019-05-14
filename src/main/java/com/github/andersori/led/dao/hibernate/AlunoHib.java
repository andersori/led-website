package com.github.andersori.led.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.AlunoDAO;
import com.github.andersori.led.entity.Aluno;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Turma;

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Aluno> listByTurma(Turma turma) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Aluno> lis = new ArrayList<Aluno>();

        try {
            Query<Aluno> qry = session.getNamedQuery("aluno_get_by_turma");
            qry.setParameter("turma", turma);

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Aluno> listByEquipe(Equipe equipe) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Aluno> lis = new ArrayList<Aluno>();

        try {
            Query<Aluno> qry = session.getNamedQuery("aluno_get_by_equipe");
            qry.setParameter("equipe", equipe);

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
