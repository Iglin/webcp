package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.Session;

/**
 * Created by alexander on 27.02.16.
 */
public class CountryDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public Session openSession() {
        return commonDAO.openSession();
    }

    public void closeSession() {
        commonDAO.closeSession();
    }

    public QueryResult add(Country country) {
        return commonDAO.add(country);
    }

    public QueryResult update(Country country) {
        return commonDAO.update(country);
    }

    public QueryResult delete(Country country) {
        return commonDAO.delete(country);
    }

    public QueryResult getById(int id) {
        return commonDAO.getById(id, Country.class);
    }

    public Country getById(int id, Session session) {
        return session.get(Country.class, id);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Country.class);
    }
}
