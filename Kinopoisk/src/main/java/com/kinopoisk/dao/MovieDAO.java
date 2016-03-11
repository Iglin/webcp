package com.kinopoisk.dao;

import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Movie;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by user on 27.02.2016.
 */
public class MovieDAO {
    private CommonDAO commonDAO = new CommonDAO();

    public QueryResult add(Movie movie) {
        return commonDAO.add(movie);
    }

    public QueryResult update(Movie movie) {
        return commonDAO.update(movie);
    }

    public QueryResult delete(Movie movie) {
        return commonDAO.delete(movie);
    }

    public QueryResult getById(int id) {
        return commonDAO.getById(id, Movie.class);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Movie.class);
    }

    public QueryResult listByTitle(String title) {
        List<Object> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.add(Restrictions.ilike("title", "%" + title + "%"));
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByActor(String actorsName) {
        List<Object> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class, "movie");
            criteria.createAlias("movie.movieid", "movieactors");
            criteria.createAlias("movieactors.actorid", "actor");
            criteria.add(Restrictions.ilike("actor.name", "%" + actorsName + "%"));
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }
}
