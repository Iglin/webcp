package com.kinopoisk.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @SequenceGenerator(name = "country_seq", sequenceName = "country_countryid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @Column(name = "countryid")
    private Integer id;
    @Column(name = "country", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "moviecountries", joinColumns = { @JoinColumn(name = "countryid") },
            inverseJoinColumns = { @JoinColumn(name = "movieid") })
    private Set<Movie> movies;

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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
