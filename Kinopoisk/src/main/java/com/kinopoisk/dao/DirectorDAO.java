package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import com.kinopoisk.model.Director;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.02.2016.
 */
public class DirectorDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Director director) {
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO director (name, dob, pic, countryid) VALUES (?, ?, ?, ?)");
            statement.setString(1, director.getName());
            statement.setDate(2, director.getDateOfBirth());
            statement.setString(3, director.getPictureURL());
            statement.setInt(4, director.getCountry().getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult update(Director director) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "UPDATE director SET name = ?, dob = ?, pic = ?, countryid = ? WHERE directorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, director.getName());
            statement.setDate(2, director.getDateOfBirth());
            statement.setString(3, director.getPictureURL());
            statement.setInt(4, director.getCountry().getId());
            statement.setInt(5, director.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult delete(Director director) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "DELETE FROM director WHERE directorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, director.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT name, dob, pic, countryid FROM director WHERE directorid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Director director = new Director();
            director.setId(id);
            while (rs.next()) {
                director.setName(rs.getString("name"));
                director.setDateOfBirth(rs.getDate("dob"));
                director.setPictureURL(rs.getString("pic"));
                int countryId = rs.getInt("countryid");
                QueryResult countryResult = new CountryDAO().getById(countryId);
                if (countryResult.isSuccess()) {
                    director.setCountry((Country)countryResult.getResult());
                } else {
                    return new QueryResult(false, "Could not get director's country: " + countryResult.getErrorMessage());
                }
            }
            connection.close();
            return new QueryResult(true, director);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getAll() {
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM actor");

            List<Director> list = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                Director actor = new Director();
                actor.setId(rs.getInt("directorid"));
                actor.setName(rs.getString("name"));
                actor.setDateOfBirth(rs.getDate("dob"));
                actor.setPictureURL(rs.getString("pic"));
                int countryId = rs.getInt("countryid");
                QueryResult countryResult = new CountryDAO().getById(countryId);
                if (countryResult.isSuccess()) {
                    actor.setCountry((Country)countryResult.getResult());
                } else {
                    return new QueryResult(false, "Could not get director's country: " + countryResult.getErrorMessage());
                }
            }
            connection.close();
            return new QueryResult(true, list);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }
}
