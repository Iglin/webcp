package com.kinopoisk.controller;

import com.kinopoisk.dao.ActorDAO;
import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Country;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by user on 08.04.2016.
 */
public class ActorEditorController extends HttpServlet {
    private CountryDAO countryDAO = new CountryDAO();
    private ActorDAO actorDAO = new ActorDAO();

    private MainController mainController = MainController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            QueryResult queryResult = saveActor(req, resp);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_actors/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        } else if (req.getParameter("delete") != null) {
            QueryResult queryResult = delete(req);
            if (queryResult.isSuccess()) {
                resp.sendRedirect("/editor_actors/show");
            } else {
                mainController.showErrorPage(req, resp, queryResult.getErrorMessage());
            }
        }
    }

    private QueryResult saveActor(HttpServletRequest request, HttpServletResponse response) {
        Actor actor = new Actor();
        actor.setName(request.getParameter("name"));
        actor.setPictureURL(request.getParameter("pic"));
        String parameter = request.getParameter("dob");
        if (!parameter.isEmpty()) {
            actor.setDateOfBirth(Date.valueOf(parameter));
        }
        QueryResult queryResult = countryDAO.getByName(request.getParameter("country"));
        if (queryResult.isSuccess()) {
            actor.setCountry((Country) queryResult.getResult());
        } else {
            mainController.showErrorPage(request, response, queryResult.getErrorMessage());
        }

        actor.setMovies(mainController.collectMoviesFromRequest(request, response));
        return actorDAO.add(actor);
    }

    private QueryResult delete(HttpServletRequest req) {
        String actorToDelete = req.getParameter("select");
        if (actorToDelete != null) {
            QueryResult queryResult = actorDAO.getByIdNoSession(Integer.parseInt(actorToDelete.split(" ")[0]));
            if (queryResult.isSuccess()) {
                if (queryResult.getResult() != null) {
                    return actorDAO.delete((Actor) queryResult.getResult());
                } else {
                    return new QueryResult(false, "No actor to delete");
                }
            } else {
                return queryResult;
            }
        } else {
            return new QueryResult(false, "No actor to delete");
        }
    }
}
