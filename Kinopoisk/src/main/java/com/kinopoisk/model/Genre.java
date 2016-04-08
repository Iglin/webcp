package com.kinopoisk.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_genreid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @Column(name = "genreid")
    private Integer id;
    @Column(name = "genre", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "moviegenres", joinColumns = { @JoinColumn(name = "genreid") },
            inverseJoinColumns = { @JoinColumn(name = "movieid") })
    private Set<Movie> movies;

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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Genre { " + id + " : " + name + " }";
    }
}
