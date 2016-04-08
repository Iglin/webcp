package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public QueryResult getByName(String countryName) {
        try (Session session = openSession()) {
            List<Country> list = session.createCriteria(Country.class)
                    .add(Restrictions.eq("name", countryName))
                    .list();
            if (list.isEmpty()) {
                return new QueryResult(false, "Country with name '" + countryName + "' not found.");
            } else {
                return new QueryResult(true, list.get(0));
            }
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Country.class);
    }
}
