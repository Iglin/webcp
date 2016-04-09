package com.kinopoisk.controller;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Country;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 08.04.2016.
 */
public class CountryEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private CountryDAO countryDAO = new CountryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            QueryResult queryResult = save(req, resp);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_countries/show");
            } else {
                mainController.showErrorPage(req, resp, "Could not save country.");
            }
        } else if (req.getParameter("delete") != null) {
            QueryResult queryResult = delete(req);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_countries/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        }
    }

    private QueryResult save(HttpServletRequest request, HttpServletResponse response) {
        Country country = new Country();
        country.setName(request.getParameter("name"));

        country.setMovies(mainController.collectMoviesFromRequest(request, response));
        return countryDAO.add(country);
    }

    private QueryResult delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            QueryResult queryResult = countryDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isSuccess()) {
                if (queryResult.getResult() != null) {
                    return countryDAO.delete((Country) queryResult.getResult());
                } else {
                    return new QueryResult(false, "No country to delete");
                }
            } else {
                return queryResult;
            }
        } else {
            return new QueryResult(false, "No country to delete");
        }
    }
}
