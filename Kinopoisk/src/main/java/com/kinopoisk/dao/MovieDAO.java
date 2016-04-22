package com.kinopoisk.dao;

import com.kinopoisk.model.Movie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 27.02.2016.
 */
public class MovieDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public Session openSession() {
        return commonDAO.openSession();
    }

    public void closeSession() {
        commonDAO.closeSession();
    }

    public int add(Movie movie) {
        return commonDAO.add(movie);
    }

    public void update(Movie movie) {
        commonDAO.update(movie);
    }

    public void delete(Movie movie) {
        commonDAO.delete(movie);
    }

    public Optional<Movie> getById(int id) {
        return commonDAO.getById(id, Movie.class);
    }

    public Optional<Movie> getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Movie.class);
    }

    public Optional<Movie> getById(int id, Session session) {
        return Optional.ofNullable(session.get(Movie.class, id));
    }

    public List<Movie> listAll() {
        return commonDAO.listAll(Movie.class);
    }

    public List<Movie> listByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.add(Restrictions.ilike("title", "%" + title + "%"));
            return criteria.list();
        }
    }

    public List<Movie> listByActor(String actorsName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("actors", "actor");
            criteria.add(Restrictions.ilike("actor.name", "%" + actorsName + "%"));
            return criteria.list();
        }
    }

    public List<Movie> listByDirector(String director) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("directors", "director");
            criteria.add(Restrictions.ilike("director.name", "%" + director + "%"));
            return criteria.list();
        }
    }

    public List<Movie> listByGenre(String genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("genres", "genre");
            criteria.add(Restrictions.ilike("genre.name", "%" + genre + "%"));
            return criteria.list();
        }
    }

    public List<Movie> listByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("countries", "country");
            criteria.add(Restrictions.ilike("country.name", "%" + country + "%"));
            return criteria.list();
        }
    }
}
