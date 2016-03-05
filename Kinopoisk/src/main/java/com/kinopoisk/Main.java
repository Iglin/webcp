package com.kinopoisk;

import com.kinopoisk.dao.ActorDAO;
import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Country;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        CountryDAO countryDAO = new CountryDAO();
        ActorDAO actorDAO = new ActorDAO();
        QueryResult queryResult = actorDAO.listAll();
        if (queryResult.isSuccess()) {
            List<Actor> list = (List<Actor>)queryResult.getResult();
            list.forEach(x -> System.out.println(x.toString()));
        }
    }
}
