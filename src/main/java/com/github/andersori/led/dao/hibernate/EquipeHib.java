package com.github.andersori.led.dao.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.EquipeDAO;
import com.github.andersori.led.entity.Equipe;

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
    public Equipe get(String nome) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Equipe e = null;

        try {
            Query<Equipe> qry = session.getNamedQuery("equipe_get_by_nome");
            qry.setParameter("nome", nome);

            try {
            	e = (Equipe) qry.getSingleResult();
            } catch(NoResultException ex) {
            	System.err.println("Nenhuma equipe cadastrada com o nome: '" + nome + "'.");
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
    public boolean validar(String username, String senha) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long cont = 0;

        try {
            Query<Long> qry = session.getNamedQuery("equipe_validar");
            qry.setParameter("nome", username);
            qry.setParameter("senha", senha);

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
