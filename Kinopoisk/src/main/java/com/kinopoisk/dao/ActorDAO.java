package com.kinopoisk.dao;

import com.kinopoisk.model.Actor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public QueryResult add(Actor actor) {
        return commonDAO.add(actor);
    }

    public QueryResult update(Actor actor) {
        return commonDAO.update(actor);
    }

    public QueryResult delete(Actor actor) {
        return commonDAO.delete(actor);
    }

    public QueryResult getById(int id) {
        return commonDAO.getById(id, Actor.class);
    }

    public Actor getById(int id, Session session) {
        return session.get(Actor.class, id);
    }

    public QueryResult getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Actor.class);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Actor.class);
    }

    public QueryResult listByName(String name) {
        List<Actor> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.add(Restrictions.ilike("name", "%" + name + "%"));
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByCountry(String country) {
        List<Actor> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.createAlias("country", "c");
            criteria.add(Restrictions.ilike("c.name", "%" + country + "%"));
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByMovie(String movie) {
        List<Actor> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Actor.class);
            criteria.createAlias("movies", "movie");
            criteria.add(Restrictions.ilike("movie.title", "%" + movie + "%"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }
}
