package com.kinopoisk.dao;

import com.kinopoisk.model.Actor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 27.02.2016.
 */
public class ActorDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public Session openSession() {
        return commonDAO.openSession();
    }

    public void closeSession() {
        commonDAO.closeSession();
    }

    public int add(Actor actor) {
        return commonDAO.add(actor);
    }

    public void update(Actor actor) {
        commonDAO.update(actor);
    }

    public void delete(Actor actor) {
        commonDAO.delete(actor);
    }

    public Optional<Actor> getById(int id) {
        return commonDAO.getById(id, Actor.class);
    }

    public Optional<Actor> getById(int id, Session session) {
        return Optional.ofNullable(session.get(Actor.class, id));
    }

    public Optional<Actor> getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Actor.class);
    }

    public List<Actor> listAll() {
        return commonDAO.listAll(Actor.class);
    }

    public List<Actor> listByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.add(Restrictions.ilike("name", "%" + name + "%"));
            return criteria.list();
        }
    }

    public List<Actor> listByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.createAlias("country", "c");
            criteria.add(Restrictions.ilike("c.name", "%" + country + "%"));
            return criteria.list();
        }
    }

    public List<Actor> listByMovie(String movie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.createAlias("movies", "movie");
            criteria.add(Restrictions.ilike("movie.title", "%" + movie + "%"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        }
    }
}
