package com.kinopoisk.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by alexander on 27.02.16.
 */
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_movieid_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @Column(name = "movieid")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "releasedate")
    private Date releaseDate;
    @Column(name = "poster")
    private String posterURL;
    @Column(name = "tagline")
    private String tagline;
    @Column(name = "details")
    private String details;
    @Column(name = "rating")
    private float rating;
    @Column(name = "agerating")
    private int ageRating;
    @Column(name = "duration")
    private int duration;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movieactors", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "actorid") })
    private List<Actor> actors;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "moviedirectors", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "directorid") })
    private List<Director> directors;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "moviegenres", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "genreid") })
    private List<Genre> genres;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "moviecountries", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "countryid") })
    private List<Country> countries;

    public Movie() {
    }

    public Movie(String title, Date releaseDate, String posterURL, String tagline, String details, float rating, int ageRating, int duration, List<Actor> actors, List<Director> directors, List<Genre> genres) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.posterURL = posterURL;
        this.tagline = tagline;
        this.details = details;
        this.rating = rating;
        this.ageRating = ageRating;
        this.duration = duration;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(id + " " + title);
        actors.forEach(x -> result.append(x.toString()));
        directors.forEach(x -> result.append(x.toString()));
        genres.forEach(x -> result.append(x.toString()));
        countries.forEach(x -> result.append(x.toString()));
        return result.toString();
    }
}
