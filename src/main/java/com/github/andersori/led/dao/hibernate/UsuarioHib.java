package com.github.andersori.led.dao.hibernate;

import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.DAO;
import com.github.andersori.led.dao.UsuarioDAO;
import com.github.andersori.led.entity.Usuario;

public class UsuarioHib implements UsuarioDAO{
	
	public DAO<Usuario> dao;

    public UsuarioHib() {
        dao = new DAOHibernate<>();
    }

    @Override
    public Usuario get(Long id) {
        return dao.get(Usuario.class, id);
    }

    @Override
    public void add(Usuario us) {
        dao.save(us);
    }

    @Override
    public void update(Usuario us) {
        dao.update(us);
    }

    @Override
    public List<Usuario> list() {
        return dao.list(new Usuario());
    }

    @Override
    public void remove(Usuario us) {
        dao.remove(us);
    }

    @Override
    public Usuario get(String username){
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Usuario c = null;

        try {
            Query<Usuario> qry = session.getNamedQuery("usuario_get_by_username");
            qry.setParameter("username", username);

            try {
            	c = (Usuario) qry.getSingleResult();
            } catch(NoResultException e) {
            	System.err.println("Nenhuma conta cadastrada com o username: '" + username + "'.");
            }
            
            t.commit();
            
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
        return c;
    }

    @Override
    public boolean validar(String username, String senha){
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        long cont = 0;

        try {
            Query<Long> qry = session.getNamedQuery("usuario_validar");
            qry.setParameter("username", username);
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
