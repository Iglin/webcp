package com.kinopoisk.controller;

import com.kinopoisk.dao.ActorDAO;
import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Actor;
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
 * Created by user on 12.03.2016.
 */
@WebServlet("/actors/")
public class ActorController extends HttpServlet {

    public ActorController() {
        super();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        QueryResult queryResult = new ActorDAO().listAll();

        resolveByResult(request, response, queryResult);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");
        QueryResult queryResult = null;
        switch (option) {
            case "Actor's name":
                queryResult = new ActorDAO().listByName(request.getParameter("input_par"));
                break;
            case "Movie title":
                queryResult = new ActorDAO().listByMovie(request.getParameter("input_par"));
                break;
            case "Country":
                queryResult = new ActorDAO().listByCountry(request.getParameter("input_par"));
                break;
        }
        resolveByResult(request, response, queryResult);
    }

    private void resolveByResult(HttpServletRequest request, HttpServletResponse response,
                                 QueryResult queryResult) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        if (queryResult.isSuccess()) {
            dispatcher = request.getRequestDispatcher("/view/actors.jsp");
            List<Actor> actors = (List<Actor>) queryResult.getResult();
            request.setAttribute("actors", actors);
        } else {
            dispatcher = request.getRequestDispatcher("/view/error.jsp");
            request.setAttribute("errorMessage", queryResult.getErrorMessage());
        }
        dispatcher.forward(request, response);
    }
}
