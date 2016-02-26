package com.kinopoisk.dao;

import com.kinopoisk.model.Actor;
import com.kinopoisk.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.02.2016.
 */
public class ActorDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Actor actor) {
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO actor (name, dob, pic, countryid) VALUES (?, ?, ?, ?)");
            statement.setString(1, actor.getName());
            statement.setDate(2, actor.getDateOfBirth());
            statement.setString(3, actor.getPictureURL());
            statement.setInt(4, actor.getCountry().getId());
            statement.executeUpdate();
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
