package com.kinopoisk.dao;

import com.kinopoisk.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 27.02.16.
 */
public class CountryDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Country country) {
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO country (country) VALUES (?)");
            statement.setString(1, country.getName());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult update(Country country) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "UPDATE country SET country = ? WHERE countryid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, country.getName());
            statement.setInt(2, country.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult delete(Country country) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "DELETE FROM country WHERE countryid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, country.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT countryid, country FROM country WHERE countryid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Country country = new Country();
            while (rs.next()) {
                country.setId(rs.getInt("countryid"));
                country.setName(rs.getString("country"));
            }
            connection.close();
            return new QueryResult(true, country);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getAll() {
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM country");

            List<Country> list = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("countryid"));
                country.setName(rs.getString("country"));
                list.add(country);
            }
            connection.close();
            return new QueryResult(true, list);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }
}
