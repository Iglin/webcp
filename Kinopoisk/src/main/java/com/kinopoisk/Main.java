package com.kinopoisk;

import com.kinopoisk.dao.CountryDAO;
import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Country;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        CountryDAO countryDAO = new CountryDAO();
        QueryResult queryResult = countryDAO.getAll();
        if (queryResult.isSuccess()) {
            List<Country> list = (List<Country>)queryResult.getResult();
            list.forEach(x -> System.out.println(x.toString()));
        }
    }
}
