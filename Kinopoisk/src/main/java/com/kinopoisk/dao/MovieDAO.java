package com.kinopoisk.dao;

import com.kinopoisk.model.Movie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public QueryResult getByIdNoSession(int id) {
        return commonDAO.getByIdNoSession(id, Movie.class);
    }

    public Movie getById(int id, Session session) {
        return session.get(Movie.class, id);
    }

    public QueryResult listAll() {
        return commonDAO.listAll(Movie.class);
    }

    public QueryResult listByTitle(String title) {
        List<Movie> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.add(Restrictions.ilike("title", "%" + title + "%"));
            list = criteria.list();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByActor(String actorsName) {
        List<Movie> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("actors", "actor");
            criteria.add(Restrictions.ilike("actor.name", "%" + actorsName + "%"));
            list = criteria.list();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByDirector(String director) {
        List<Movie> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("directors", "director");
            criteria.add(Restrictions.ilike("director.name", "%" + director + "%"));
            list = criteria.list();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByGenre(String genre) {
        List<Movie> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("genres", "genre");
            criteria.add(Restrictions.ilike("genre.name", "%" + genre + "%"));
            list = criteria.list();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByCountry(String country) {
        List<Movie> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Movie.class);
            criteria.createAlias("countries", "country");
            criteria.add(Restrictions.ilike("country.name", "%" + country + "%"));
            list = criteria.list();
        } catch (Exception e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }
}
