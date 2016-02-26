package com.kinopoisk.model;

/**
 * Created by alexander on 26.02.16.
 */
public class Genre {
    private Integer id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {

        this.name = name;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
