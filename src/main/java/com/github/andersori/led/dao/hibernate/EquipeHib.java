package com.github.andersori.led.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.entity.Casa;
import com.github.andersori.led.entity.Equipe;
import com.github.andersori.led.entity.Maratona;
import com.github.andersori.led.entity.Usuario;

public class EquipeHib implements EquipeDAO{

	private DAO<Equipe> dao;

    public EquipeHib(){
        dao = new DAOHibernate<>();
    }

    @Override
    public Equipe get(Long id) {
        return dao.get(Equipe.class, id);
    }

    @Override
    public void add(Equipe eq) {
        dao.save(eq);
    }

    @Override
    public void update(Equipe eq) {
        dao.update(eq);
    }

    @Override
    public List<Equipe> list() {
        return dao.list(new Equipe());
    }

    @Override
    public void remove(Equipe eq) {
        dao.remove(eq);
    }

    @Override
    public Equipe get(Usuario user) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Equipe e = null;

        try {
            Query<Equipe> qry = session.getNamedQuery("equipe_get_by_user");
            qry.setParameter("usuario", user);

            try {
            	e = (Equipe) qry.getSingleResult();
            } catch(NoResultException ex) {
            	System.err.println("O usuario " + user.getNome() +" não possui equipe vinculada a ele.");
            }
            
            t.commit();
            
        } catch (RuntimeException ex) {
            t.rollback();
            throw ex;
        } finally {
            session.close();
        }
        return e;
    }

	@Override
	public List<Equipe> listByMaratona(Maratona maratona) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Equipe> lis = new ArrayList<Equipe>();

        try {
            Query<Equipe> qry = session.getNamedQuery("equipe_get_by_maratona");
            qry.setParameter("maratona", maratona);
            
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
	public List<Equipe> listByMaratonaCasa(Maratona maratona, Casa casa) {
		Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Equipe> lis = new ArrayList<Equipe>();

        try {
            Query<Equipe> qry = session.getNamedQuery("equipe_get_by_maratona_casa");
            qry.setParameter("maratona", maratona);
            qry.setParameter("casa", casa);
            
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
