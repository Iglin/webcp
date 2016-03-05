package com.kinopoisk.dao;

import com.kinopoisk.model.*;

import java.sql.*;
import java.util.ArrayList;
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
}
