package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 27.02.16.
 */
public class CountryDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Country country) {
        int id;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            id = (Integer) session.save(country);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, id);
    }

    public QueryResult update(Country country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(country);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, null);
    }

    public QueryResult delete(Country country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(country);
            session.getTransaction().commit();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, null);
    }

    public QueryResult getById(int id) {
        Country country;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            country = session.get(Country.class, id);
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, country);
    }

    public QueryResult getAll() {
        List<Country> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createCriteria(Country.class).list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }
}
