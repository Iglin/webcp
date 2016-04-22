package com.kinopoisk.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * Created by root on 05.03.16.
 */
public class CommonDAO {

    private Session session;

    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSession() {
        if (session.isOpen()) {
            session.close();
        }
    }

    public int add(Object object) {
        try (Session session = openSession()) {
            session.beginTransaction();
            int id = (Integer) session.save(object);
            session.getTransaction().commit();
            return id;
        }
    }

    public void update(Object object) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }
    }

    public void delete(Object object) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        }
    }

    public Optional getById(int id, Class objClass) {
        return Optional.ofNullable(session.get(objClass, id));
    }

    public Optional getByIdNoSession(int id, Class objClass) {
        try (Session session = openSession()) {
            return Optional.ofNullable(session.get(objClass, id));
        }
    }

    public List listAll(Class objClass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createCriteria(objClass).list();
        }
    }
}
