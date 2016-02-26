package com.kinopoisk.dao;

import com.kinopoisk.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.02.2016.
 */
public class MovieDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Movie movie) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "INSERT INTO movie (title, releasedate, poster, tagline, details, rating, agerating, duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getTitle());
            statement.setDate(2, movie.getReleaseDate());
            statement.setString(3, movie.getPosterURL());
            statement.setString(4, movie.getTagline());
            statement.setString(5, movie.getDetails());
            statement.setFloat(6, movie.getRating());
            statement.setInt(7, movie.getAgeRating());
            statement.setInt(8, movie.getDuration());
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO moviegenres VALUES (?, ?)");
            for (Genre genre : movie.getGenres()) {
                statement.setInt(1, movie.getId());
                statement.setInt(2, genre.getId());
                statement.addBatch();
            }
            statement.executeBatch();

            statement = connection.prepareStatement("INSERT INTO moviedirectors VALUES (?, ?)");
            for (Director director : movie.getDirectors()) {
                statement.setInt(1, movie.getId());
                statement.setInt(2, director.getId());
                statement.addBatch();
            }
            statement.executeBatch();

            statement = connection.prepareStatement("INSERT INTO movieactors VALUES (?, ?)");
            for (Actor actor : movie.getActors()) {
                statement.setInt(1, movie.getId());
                statement.setInt(2, actor.getId());
                statement.addBatch();
            }
            statement.executeBatch();

            statement = connection.prepareStatement("INSERT INTO moviecountries VALUES (?, ?)");
            for (Country country : movie.getCountries()) {
                statement.setInt(1, movie.getId());
                statement.setInt(2, country.getId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult update(Actor actor) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "UPDATE actor SET name = ?, dob = ?, pic = ?, countryid = ? WHERE actorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, actor.getName());
            statement.setDate(2, actor.getDateOfBirth());
            statement.setString(3, actor.getPictureURL());
            statement.setInt(4, actor.getCountry().getId());
            statement.setInt(5, actor.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult delete(Actor actor) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "DELETE FROM actor WHERE actorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, actor.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT name, dob, pic, countryid FROM actor WHERE actorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Actor actor = new Actor();
            actor.setId(id);
            while (rs.next()) {
                actor.setName(rs.getString("name"));
                actor.setDateOfBirth(rs.getDate("dob"));
                actor.setPictureURL(rs.getString("pic"));
                int countryId = rs.getInt("countryid");
                QueryResult countryResult = new CountryDAO().getById(countryId);
                if (countryResult.isSuccess()) {
                    actor.setCountry((Country)countryResult.getResult());
                } else {
                    return new QueryResult(false, "Could not get actor's country: " + countryResult.getErrorMessage());
                }
            }
            connection.close();
            return new QueryResult(true, actor);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getAll() {
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM actor");

            List<Actor> list = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setId(rs.getInt("actorid"));
                actor.setName(rs.getString("name"));
                actor.setDateOfBirth(rs.getDate("dob"));
                actor.setPictureURL(rs.getString("pic"));
                int countryId = rs.getInt("countryid");
                QueryResult countryResult = new CountryDAO().getById(countryId);
                if (countryResult.isSuccess()) {
                    actor.setCountry((Country)countryResult.getResult());
                } else {
                    return new QueryResult(false, "Could not get actor's country: " + countryResult.getErrorMessage());
                }
            }
            connection.close();
            return new QueryResult(true, list);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }
}
