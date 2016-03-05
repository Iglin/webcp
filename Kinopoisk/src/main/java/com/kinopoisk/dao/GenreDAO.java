package com.kinopoisk.dao;

import com.kinopoisk.model.Genre;

/**
 * Created by alexander on 27.02.16.
 */
public class GenreDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public QueryResult add(Genre genre) {
        return commonDAO.add(genre);
    }

    public QueryResult update(Genre genre) {
        return commonDAO.update(genre);
    }

    public QueryResult delete(Genre genre) {
        return commonDAO.delete(genre);
    }

    public QueryResult getById(int id) {
        return commonDAO.getById(id, Genre.class);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Genre.class);
    }
}
