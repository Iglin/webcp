package com.kinopoisk.dao;

import com.kinopoisk.model.Genre;
import org.hibernate.Session;

/**
 * Created by alexander on 27.02.16.
 */
public class GenreDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public Session openSession() {
        return commonDAO.openSession();
    }

    public void closeSession() {
        commonDAO.closeSession();
    }

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

    public Genre getById(int id, Session session) {
        return session.get(Genre.class, id);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Genre.class);
    }
}
