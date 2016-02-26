package com.kinopoisk.model;

import java.sql.Date;

/**
 * Created by alexander on 26.02.16.
 */
public class Actor {
    private Integer id;
    private String name;
    private Date dateOfBirth;
    private String pictureURL;
    private Country country;

    public Actor() {
    }

    public Actor(String name, Date dateOfBirth, String pictureURL, Country country) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.pictureURL = pictureURL;
        this.country = country;
    }

    public Actor(String name) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
