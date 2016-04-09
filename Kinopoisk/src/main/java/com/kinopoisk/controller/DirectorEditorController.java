package com.kinopoisk.controller;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.DirectorDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Country;
import com.kinopoisk.model.Director;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by user on 08.04.2016.
 */
public class DirectorEditorController extends HttpServlet {
    private MainController mainController = MainController.getInstance();
    private CountryDAO countryDAO = new CountryDAO();
    private DirectorDAO directorDAO = new DirectorDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            QueryResult queryResult = saveDirector(req, resp);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_directors/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        } else if (req.getParameter("delete") != null) {
            QueryResult queryResult = delete(req);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_directors/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        }
    }

    private QueryResult saveDirector(HttpServletRequest request, HttpServletResponse response) {
        Director director = new Director();
        director.setName(request.getParameter("name"));
        director.setPictureURL(request.getParameter("pic"));
        String parameter = request.getParameter("dob");
        if (!parameter.isEmpty()) {
            director.setDateOfBirth(Date.valueOf(parameter));
        }
        QueryResult queryResult = countryDAO.getByName(request.getParameter("country"));
        if (queryResult.isSuccess()) {
            director.setCountry((Country) queryResult.getResult());
        } else {
            mainController.showErrorPage(request, response, queryResult.getErrorMessage());
        }


        director.setMovies(mainController.collectMoviesFromRequest(request, response));
        return directorDAO.add(director);
    }

    private QueryResult delete(HttpServletRequest req) {
        String directorToDelete = req.getParameter("select");
        if (directorToDelete != null) {
            QueryResult queryResult = directorDAO.getByIdNoSession(Integer.parseInt(directorToDelete.split(" ")[0]));
            if (queryResult.isSuccess()) {
                if (queryResult.getResult() != null) {
                    return directorDAO.delete((Director) queryResult.getResult());
                } else {
                    return new QueryResult(false, "No director to delete");
                }
            } else {
                return queryResult;
            }
        } else {
            return new QueryResult(false, "No director to delete");
        }
    }
}
