package com.kinopoisk.controller;

import com.kinopoisk.dao.ActorDAO;
import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Country;

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
public class ActorEditorController extends HttpServlet {
    private CountryDAO countryDAO = new CountryDAO();
    private ActorDAO actorDAO = new ActorDAO();

    private MainController mainController = MainController.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            saveActor(req, resp);
            resp.sendRedirect("/editor_actors/show");
        } else if (req.getParameter("delete") != null) {
            delete(req);
            resp.sendRedirect("/editor_actors/show");
        }
    }

    private void saveActor(HttpServletRequest request, HttpServletResponse response) {
        Actor actor = new Actor();
        actor.setName(request.getParameter("name"));
        actor.setPictureURL(request.getParameter("pic"));
        String parameter = request.getParameter("dob");
        if (!parameter.isEmpty()) {
            actor.setDateOfBirth(Date.valueOf(parameter));
        }
        Optional<Country> queryResult = countryDAO.getByName(request.getParameter("country"));
        if (queryResult.isPresent()) {
            actor.setCountry(queryResult.get());
        }

        actor.setMovies(mainController.collectMoviesFromRequest(request, response));
        actorDAO.add(actor);
    }

    private void delete(HttpServletRequest req) {
        String actorToDelete = req.getParameter("select");
        if (actorToDelete != null) {
            Optional<Actor> queryResult = actorDAO.getByIdNoSession(Integer.parseInt(actorToDelete.split(" ")[0]));
            if (queryResult.isPresent()) {
                actorDAO.delete(queryResult.get());
            }
        }
    }
}
