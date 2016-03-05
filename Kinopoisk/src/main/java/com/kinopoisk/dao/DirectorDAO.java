package com.kinopoisk.dao;

import com.kinopoisk.model.Director;

/**
 * Created by user on 27.02.2016.
 */
public class DirectorDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public QueryResult add(Director director) {
        return commonDAO.add(director);
    }

    public QueryResult update(Director director) {
        return commonDAO.update(director);
    }

    public QueryResult delete(Director director) {
        return commonDAO.delete(director);
    }

    public QueryResult getById(int id) {
        return commonDAO.getById(id, Director.class);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Director.class);
    }
}
