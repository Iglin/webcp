package com.kinopoisk.controller;

import com.kinopoisk.dao.GenreDAO;
import com.kinopoisk.model.Genre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by user on 08.04.2016.
 */
public class GenreEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private GenreDAO genreDAO = new GenreDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("save") != null) {
            save(req, resp);
            resp.sendRedirect("/editor_genres/show");
        } else if (req.getParameter("delete") != null) {
            delete(req);
            resp.sendRedirect("/editor_genres/show");
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        Genre genre = new Genre();
        genre.setName(request.getParameter("name"));

        genre.setMovies(mainController.collectMoviesFromRequest(request, response));
        genreDAO.add(genre);
    }

    private void delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            Optional<Genre> queryResult = genreDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isPresent()) {
                genreDAO.delete(queryResult.get());
            }
        }
    }
}
