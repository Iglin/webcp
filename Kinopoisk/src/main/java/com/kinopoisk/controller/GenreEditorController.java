package com.kinopoisk.controller;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.DirectorDAO;
import com.kinopoisk.dao.GenreDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Country;
import com.kinopoisk.model.Director;
import com.kinopoisk.model.Genre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by user on 08.04.2016.
 */
public class GenreEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private GenreDAO genreDAO = new GenreDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            QueryResult queryResult = save(req, resp);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_genres/show");
            } else {
                mainController.showErrorPage(req, resp, "Could not save genre.");
            }
        } else if (req.getParameter("delete") != null) {
            QueryResult queryResult = delete(req);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_genres/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        }
    }

    private QueryResult save(HttpServletRequest request, HttpServletResponse response) {
        Genre genre = new Genre();
        genre.setName(request.getParameter("name"));

        genre.setMovies(mainController.collectMoviesFromRequest(request, response));
        return genreDAO.add(genre);
    }

    private QueryResult delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            QueryResult queryResult = genreDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isSuccess()) {
                if (queryResult.getResult() != null) {
                    return genreDAO.delete((Genre) queryResult.getResult());
                } else {
                    return new QueryResult(false, "No genre to delete");
                }
            } else {
                return queryResult;
            }
        } else {
            return new QueryResult(false, "No genre to delete");
        }
    }
}
