package com.kinopoisk.dao;

import com.kinopoisk.model.Director;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public QueryResult listByName(String name) {
        List<Director> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
            criteria.add(Restrictions.ilike("name", "%" + name + "%"));
           // criteria.setFetchMode("movies", FetchMode.SELECT);
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByCountry(String country) {
        List<Director> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
            criteria.createAlias("country", "c");
            criteria.add(Restrictions.ilike("c.name", "%" + country + "%"));
            list = criteria.list();
        } catch (ExceptionInInitializerError e) {
            return new QueryResult(false, e.getMessage());
        }
        return new QueryResult(true, list);
    }

    public QueryResult listByMovie(String movie) {
        List<Director> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Director.class);
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
