package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

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

    public int add(Country country) {
        return commonDAO.add(country);
    }

    public void update(Country country) {
        commonDAO.update(country);
    }

    public void delete(Country country) {
        commonDAO.delete(country);
    }

    public Optional<Country> getById(int id) {
        return commonDAO.getById(id, Country.class);
    }

    public Optional<Country> getById(int id, Session session) {
        return Optional.ofNullable(session.get(Country.class, id));
    }

    public Optional<Country> getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Country.class);
    }

    public Optional<Country> getByName(String countryName) {
        try (Session session = openSession()) {
            List<Country> list = session.createCriteria(Country.class)
                    .add(Restrictions.eq("name", countryName))
                    .list();
            if (list.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.ofNullable(list.get(0));
            }
        }
    }

    public List<Country> listAll() {
        return commonDAO.listAll(Country.class);
    }
}
