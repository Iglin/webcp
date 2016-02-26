package com.kinopoisk;

import com.kinopoisk.dao.QueryResult;
import com.kinopoisk.model.Country;

public class Main {

    public static void main(String[] args) {
        Country country = new Country("USA");

        System.out.println(country.getId());
      //  CountryDAO countryDAO = new CountryDAO();
    //    QueryResult result = countryDAO.add(country);
     //   if (result.isSuccess()) {
     //       System.out.println("OK");
     //   } else {
      //      System.out.println();
     //   }
    }
}
