package com.kinopoisk.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @SequenceGenerator(name = "country_seq", sequenceName = "country_countryid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @Column(name = "countryid")
    private Integer id;
    @Column(name = "country")
    private String name;

    public Country() {
    }

    public Country(String name) {
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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
