package com.kinopoisk.model;

import javax.persistence.*;

/**
 * Created by alexander on 26.02.16.
 */
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_genreid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @Column(name = "genreid")
    private Integer id;
    @Column(name = "genre")
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
