package com.kinopoisk.controller;

import com.kinopoisk.dao.*;
import com.kinopoisk.model.*;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 06.04.2016.
 */
public class MovieEditorController extends HttpServlet {
    private GenreDAO genreDAO = new GenreDAO();
    private CountryDAO countryDAO = new CountryDAO();
    private ActorDAO actorDAO = new ActorDAO();
    private DirectorDAO directorDAO = new DirectorDAO();
    private MovieDAO movieDAO = new MovieDAO();

    private MainController mainController = MainController.getInstance();

    private QueryResult delete(HttpServletRequest req) {
        String movieToDelete = req.getParameter("select");
        if (movieToDelete != null) {
            QueryResult queryResult = movieDAO.getByIdNoSession(Integer.parseInt(movieToDelete.split(" ")[0]));
            if (queryResult.isSuccess()) {
                if (queryResult.getResult() != null) {
                    return movieDAO.delete((Movie) queryResult.getResult());
                } else {
                    return new QueryResult(false, "No movie to delete");
                }
            } else {
                return queryResult;
            }
        } else {
            return new QueryResult(false, "No movie to delete");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            QueryResult queryResult = saveMovie(req, resp);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_movies/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        } else if (req.getParameter("delete") != null) {
            QueryResult queryResult = delete(req);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_movies/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        }
    }

    private QueryResult saveMovie(HttpServletRequest request, HttpServletResponse response) {
        Movie movie = new Movie();
        movie.setTitle(request.getParameter("title"));
        String parameter = request.getParameter("tagline");
        if (parameter != null) {
            movie.setTagline(request.getParameter("tagline"));
        }
        movie.setPosterURL(request.getParameter("poster"));
        movie.setDetails(request.getParameter("description"));
        parameter = request.getParameter("date");
        if (!parameter.isEmpty()) {
            movie.setReleaseDate(Date.valueOf(parameter));
        }
        parameter = request.getParameter("rating");
        if (!parameter.isEmpty()) {
            movie.setRating(Float.parseFloat(parameter));
        }
        parameter = request.getParameter("age");
        if (!parameter.isEmpty()) {
            movie.setAgeRating(Integer.parseInt(parameter));
        }
        parameter = request.getParameter("duration");
        if (!parameter.isEmpty()) {
            movie.setDuration(Integer.parseInt(parameter));
        }

        Set<Genre> genres = new HashSet<>();
        Set<Country> countries = new HashSet<>();
        Set<Actor> actors = new HashSet<>();
        Set<Director> directors = new HashSet<>();

        Enumeration enumeration = request.getParameterNames();
        int id;
        try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                if (parameterName.contains("genre")) {
                    id = Integer.parseInt(parameterName.replace("genre", ""));
                    Genre genre = genreDAO.getById(id, hibernateSession);
                    genres.add(genre);
                } else if (parameterName.contains("country")) {
                    id = Integer.parseInt(parameterName.replace("country", ""));
                    countries.add(countryDAO.getById(id, hibernateSession));
                } else if (parameterName.contains("actor")) {
                    id = Integer.parseInt(parameterName.replace("actor", ""));
                    actors.add(actorDAO.getById(id, hibernateSession));
                } else if (parameterName.contains("director")) {
                    id = Integer.parseInt(parameterName.replace("director", ""));
                    directors.add(directorDAO.getById(id, hibernateSession));
                }
            }
        } catch (Exception e) {
            mainController.showErrorPage(request, response, "Could not connect to database.");
        }
        movie.setGenres(genres);
        movie.setCountries(countries);
        movie.setActors(actors);
        movie.setDirectors(directors);

        return movieDAO.add(movie);
    }
}
