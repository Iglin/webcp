package com.kinopoisk.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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
    private Float rating;
    @Column(name = "agerating")
    private Integer ageRating;
    @Column(name = "duration")
    private Integer duration;
    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "movieactors", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "actorid") })
    private Set<Actor> actors;
    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "moviedirectors", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "directorid") })
    private Set<Director> directors;

    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "moviecountries", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "countryid") })
    private Set<Country> countries;

    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.MERGE)
    @JoinTable(name = "moviegenres", joinColumns = { @JoinColumn(name = "movieid") },
            inverseJoinColumns = { @JoinColumn(name = "genreid") })
    private Set<Genre> genres;

    public Movie() {
    }

    public Movie(String title, Date releaseDate, String posterURL, String tagline, String details, float rating, int ageRating, int duration, Set<Actor> actors, Set<Director> directors, Set<Genre> genres) {

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

    public Float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
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
