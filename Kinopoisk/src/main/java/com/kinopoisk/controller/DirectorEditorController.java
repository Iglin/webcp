package com.kinopoisk.controller;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.DirectorDAO;
import com.kinopoisk.model.Country;
import com.kinopoisk.model.Director;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

/**
 * Created by user on 08.04.2016.
 */
public class DirectorEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private CountryDAO countryDAO = new CountryDAO();
    private DirectorDAO directorDAO = new DirectorDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("save") != null) {
            save(req, resp);
            resp.sendRedirect("/editor_directors/show");
        } else if (req.getParameter("delete") != null) {
            delete(req);
            resp.sendRedirect("/editor_directors/show");
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        Director director = new Director();
        director.setName(request.getParameter("name"));
        director.setPictureURL(request.getParameter("pic"));
        String parameter = request.getParameter("dob");
        if (!parameter.isEmpty()) {
            director.setDateOfBirth(Date.valueOf(parameter));
        }
        Optional<Country> queryResult = countryDAO.getByName(request.getParameter("country"));
        if (queryResult.isPresent()) {
            director.setCountry(queryResult.get());
        }
        director.setMovies(mainController.collectMoviesFromRequest(request, response));
        directorDAO.add(director);
    }

    private void delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            Optional<Director> queryResult = directorDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isPresent()) {
                directorDAO.delete(queryResult.get());
            }
        }
    }
}
