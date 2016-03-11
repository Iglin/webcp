package com.kinopoisk.controller;

import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Movie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 11.03.2016.
 */
public class MovieController extends HttpServlet {

    public MovieController() {
        super();
    }

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        QueryResult queryResult = new MovieDAO().listAll();
        if (queryResult.isSuccess()) {
            dispatcher = request.getRequestDispatcher("/view/index.jsp");
            List<Movie> movies = (List<Movie>) queryResult.getResult();
            request.setAttribute("movies", movies);
        } else {
            dispatcher = request.getRequestDispatcher("/view/error.jsp");
            request.setAttribute("errorMessage", queryResult.getErrorMessage());
        }
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String option = request.getParameter("option");
        QueryResult queryResult = null;
        switch (option) {
            case "Actor":
                queryResult = new MovieDAO().listByTitle(request.getParameter("input_par"));
                break;
            case "Movie title":
                queryResult = new MovieDAO().listByTitle(request.getParameter("input_par"));
                break;
        }
        if (queryResult.isSuccess()) {
            dispatcher = request.getRequestDispatcher("/view/index.jsp");
            List<Movie> movies = (List<Movie>) queryResult.getResult();
            request.setAttribute("movies", movies);
        } else {
            dispatcher = request.getRequestDispatcher("/view/error.jsp");
            request.setAttribute("errorMessage", queryResult.getErrorMessage());
        }
        dispatcher.forward(request, response);
    }
}