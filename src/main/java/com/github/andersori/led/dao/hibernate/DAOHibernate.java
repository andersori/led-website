package com.github.andersori.led.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.github.andersori.led.config.Hibernate;
import com.github.andersori.led.dao.DAO;

public class DAOHibernate<T> implements DAO<T> {

	@Override
    public T get(Class<T> type, Long id) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            T o = (T) session.get(type, id);
            t.commit();
            return o;
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(T obj) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            if (obj != null) {
                session.persist(obj);
                t.commit();
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T obj) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            if (obj != null) {
                session.merge(obj);
                t.commit();
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(T obj) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            if (obj != null) {
                session.delete(obj);
                t.commit();
            } else {
                throw new NullPointerException("Objeto não pode ser nulo!");
            }
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<T> list(T obj) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();

        try {
            Example example = Example.create(obj).excludeZeroes().ignoreCase();
            List<T> objetos = session.createCriteria(obj.getClass()).add(example).list();
            t.commit();
            return objetos;
        } catch (RuntimeException e) {
            t.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
	
}
