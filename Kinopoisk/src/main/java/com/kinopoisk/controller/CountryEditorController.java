package com.kinopoisk.controller;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.model.Country;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by user on 08.04.2016.
 */
public class CountryEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private CountryDAO countryDAO = new CountryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("save") != null) {
            save(req, resp);
            resp.sendRedirect("/editor_countries/show");
        } else if (req.getParameter("delete") != null) {
            delete(req);
            resp.sendRedirect("/editor_countries/show");
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        Country country = new Country();
        country.setName(request.getParameter("name"));

        country.setMovies(mainController.collectMoviesFromRequest(request, response));
        countryDAO.add(country);
    }

    private void delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            Optional<Country> queryResult = countryDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isPresent()) {
                countryDAO.delete(queryResult.get());
            }
        }
    }
}
