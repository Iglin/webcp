package com.kinopoisk.dao;

import com.kinopoisk.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 27.02.16.
 */
public class GenreDAO {
    private DataBaseConnection dbConnection = new DataBaseConnection();

    public QueryResult add(Genre genre) {
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO genre (genre) VALUES (?)");
            statement.setString(1, genre.getName());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult update(Genre genre) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "UPDATE genre SET genre = ? WHERE genreid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, genre.getName());
            statement.setInt(2, genre.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult delete(Genre genre) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "DELETE FROM genre WHERE genreid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, genre.getId());
            statement.executeUpdate();
            connection.close();
            return new QueryResult(true);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT genreid, genre FROM genre WHERE genreid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            Genre genre = new Genre();
            while (rs.next()) {
                genre.setId(rs.getInt("genreid"));
                genre.setName(rs.getString("genre"));
            }
            connection.close();
            return new QueryResult(true, genre);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }

    public QueryResult getAll() {
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM genre");

            List<Genre> list = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("genreid"));
                genre.setName(rs.getString("genre"));
                list.add(genre);
            }
            connection.close();
            return new QueryResult(true, list);
        } catch (SQLException e) {
            return new QueryResult(false, e.getMessage());
        }
    }
}
