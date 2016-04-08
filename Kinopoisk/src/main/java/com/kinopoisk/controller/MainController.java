package com.kinopoisk.controller;

import com.kinopoisk.dao.HibernateUtil;
import com.kinopoisk.dao.MovieDAO;
import com.kinopoisk.model.Movie;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 08.04.2016.
 */
public enum MainController {
    INSTANCE;

    private MovieDAO movieDAO = new MovieDAO();

    public static MainController getInstance() {
        return INSTANCE;
    }

    public Set<Movie> collectMoviesFromRequest(HttpServletRequest request, HttpServletResponse response) {
        Set<Movie> movies = new HashSet<>();

        Enumeration enumeration = request.getParameterNames();
        int id;
        try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                if (parameterName.contains("movie")) {
                    id = Integer.parseInt(parameterName.replace("movie", ""));
                    Movie movie = movieDAO.getById(id, hibernateSession);
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            showErrorPage(request, response, "Could not connect to database.");
        }
        return movies;
    }

    public  void showErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
        request.setAttribute("errorMessage", errorMessage);
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
