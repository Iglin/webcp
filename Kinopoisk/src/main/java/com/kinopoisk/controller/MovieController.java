package com.kinopoisk.controller;

import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Movie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 11.03.2016.
 */
@WebServlet("/movies/")
public class MovieController extends HttpServlet {

    public MovieController() {
        super();
    }

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryResult queryResult = new MovieDAO().listAll();

        resolveByResult(request, response, queryResult);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");
        QueryResult queryResult = null;
        switch (option) {
            case "Actor":
                queryResult = new MovieDAO().listByActor(request.getParameter("input_par"));
                break;
            case "Movie title":
                queryResult = new MovieDAO().listByTitle(request.getParameter("input_par"));
                break;
            case "Director":
                queryResult = new MovieDAO().listByDirector(request.getParameter("input_par"));
                break;
            case "Genre":
                queryResult = new MovieDAO().listByGenre(request.getParameter("input_par"));
                break;
            case "Country":
                queryResult = new MovieDAO().listByCountry(request.getParameter("input_par"));
                break;
        }
        resolveByResult(request, response, queryResult);
    }

    private void resolveByResult(HttpServletRequest request, HttpServletResponse response, QueryResult queryResult) throws ServletException, IOException {
        RequestDispatcher dispatcher;
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