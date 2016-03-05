package com.kinopoisk.dao;

import com.kinopoisk.model.Actor;

/**
 * Created by user on 27.02.2016.
 */
public class ActorDAO {
    private CommonDAO commonDAO = new CommonDAO();

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

    public QueryResult listAll() {
        return commonDAO.listAll(Actor.class);
    }
}
