package com.kinopoisk;

import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Movie;

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
