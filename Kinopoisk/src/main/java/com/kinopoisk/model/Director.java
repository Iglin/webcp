package com.kinopoisk.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by alexander on 26.02.16.
 */
@Entity
@Table(name = "director")
public class Director {
    @Id
    @SequenceGenerator(name = "director_seq", sequenceName = "director_directorid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "director_seq")
    @Column(name = "directorid")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "dob")
    private Date dateOfBirth;
    @Column(name = "pic")
    private String pictureURL;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryid")
    private Country country;

    public Director() {
    }

    public Director(String name, Date dateOfBirth, String pictureURL, Country country) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.pictureURL = pictureURL;
        this.country = country;
    }

    public Director(String name) {
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
