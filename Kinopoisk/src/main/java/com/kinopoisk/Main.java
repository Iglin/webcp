package com.kinopoisk;

import com.kinopoisk.dao.ActorDAO;
import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Country;
import com.kinopoisk.model.Movie;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();
        QueryResult queryResult = movieDAO.listAll();
        if (queryResult.isSuccess()) {
            List<Movie> list = (List<Movie>) queryResult.getResult();
            list.forEach(movie -> System.out.println(movie.toString()));
        } else {
            System.err.println(queryResult.getErrorMessage());
        }
    }
}
