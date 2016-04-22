package com.kinopoisk.dao;

import com.kinopoisk.model.Director;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 27.02.2016.
 */
public class DirectorDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public Session openSession() {
        return commonDAO.openSession();
    }

    public void closeSession() {
        commonDAO.closeSession();
    }

    public int add(Director director) {
        return commonDAO.add(director);
    }

    public void update(Director director) {
        commonDAO.update(director);
    }

    public void delete(Director director) {
        commonDAO.delete(director);
    }

    public Optional<Director> getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Director.class);
    }

    public Optional<Director> getById(int id) {
        return commonDAO.getById(id, Director.class);
    }

    public Optional<Director> getById(int id, Session session) {
        return Optional.ofNullable(session.get(Director.class, id));
    }

    public List<Director> listAll() {
        return commonDAO.listAll(Director.class);
    }

    public List<Director> listByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
            criteria.add(Restrictions.ilike("name", "%" + name + "%"));
            return criteria.list();
        }
    }

    public List<Director> listByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
            criteria.createAlias("country", "c");
            criteria.add(Restrictions.ilike("c.name", "%" + country + "%"));
            return criteria.list();
        }
    }

    public List<Director> listByMovie(String movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
            criteria.createAlias("movies", "movie");
            criteria.add(Restrictions.ilike("movie.title", "%" + movie + "%"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        }
    }
}
