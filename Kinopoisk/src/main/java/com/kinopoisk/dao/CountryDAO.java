package com.kinopoisk.dao;

import com.kinopoisk.model.Country;

/**
 * Created by alexander on 27.02.16.
 */
public class CountryDAO {
    private CommonDAO commonDAO = new CommonDAO();

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

    public QueryResult listAll() {
        return commonDAO.listAll(Country.class);
    }
}
