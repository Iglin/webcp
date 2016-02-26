package com.kinopoisk.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by alexander on 27.02.16.
 */
public class Movie {
    private Integer id;
    private String title;
    private Date releaseDate;
    private String posterURL;
    private String tagline;
    private String details;
    private float rating;
    private int ageRating;
    private int duration;
    private List<Actor> actors;
    private List<Director> directors;
    private List<Genre> genres;

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
}
