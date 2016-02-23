package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.Session;

public class CountryDAO {

    public QueryResult add(Country country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.save(country);
            session.getTransaction().commit();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, null);
    }

}
