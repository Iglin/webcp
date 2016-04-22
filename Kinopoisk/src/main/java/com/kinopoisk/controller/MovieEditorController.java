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
import java.util.Optional;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("save") != null) {
            saveMovie(req, resp);
            resp.sendRedirect("/editor_movies/show");
        } else if (req.getParameter("delete") != null) {
            delete(req);
            resp.sendRedirect("/editor_movies/show");
        }
    }

    private void saveMovie(HttpServletRequest request, HttpServletResponse response) {
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
                    Optional<Genre> genre = genreDAO.getById(id, hibernateSession);
                    if (genre.isPresent()) {
                        genres.add(genre.get());
                    }
                } else if (parameterName.contains("country")) {
                    id = Integer.parseInt(parameterName.replace("country", ""));
                    Optional<Country> country = countryDAO.getById(id, hibernateSession);
                    if (country.isPresent()) {
                        countries.add(country.get());
                    }
                } else if (parameterName.contains("actor")) {
                    id = Integer.parseInt(parameterName.replace("actor", ""));
                    Optional<Actor> actor = actorDAO.getById(id, hibernateSession);
                    if (actor.isPresent()) {
                        actors.add(actor.get());
                    }
                } else if (parameterName.contains("director")) {
                    id = Integer.parseInt(parameterName.replace("director", ""));
                    Optional<Director> director = directorDAO.getById(id, hibernateSession);
                    if (director.isPresent()) {
                        directors.add(director.get());
                    }
                }
            }
        } catch (Exception e) {
            mainController.showErrorPage(request, response, "Could not connect to database.");
        }
        movie.setGenres(genres);
        movie.setCountries(countries);
        movie.setActors(actors);
        movie.setDirectors(directors);

        movieDAO.add(movie);
    }

    private void delete(HttpServletRequest req) {
        String movieToDelete = req.getParameter("select");
        if (movieToDelete != null) {
            Optional<Movie> queryResult = movieDAO.getByIdNoSession(Integer.parseInt(movieToDelete.split(" ")[0]));
            if (queryResult.isPresent()) {
                movieDAO.delete(queryResult.get());
            }
        }
    }
}
