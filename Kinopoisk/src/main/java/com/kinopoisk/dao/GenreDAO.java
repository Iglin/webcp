package com.kinopoisk.dao;

import com.kinopoisk.model.Genre;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

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

    public int add(Genre genre) {
        return commonDAO.add(genre);
    }

    public void update(Genre genre) {
        commonDAO.update(genre);
    }

    public void delete(Genre genre) {
        commonDAO.delete(genre);
    }

    public Optional<Genre> getById(int id) {
        return commonDAO.getById(id, Genre.class);
    }

    public Optional<Genre> getById(int id, Session session) {
        return Optional.ofNullable(session.get(Genre.class, id));
    }

    public Optional<Genre> getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Genre.class);
    }

    public List<Genre> listAll() {
        return commonDAO.listAll(Genre.class);
    }
}
