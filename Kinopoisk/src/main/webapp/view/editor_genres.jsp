<%@ page import="com.kinopoisk.dao.GenreDAO" %>
<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.model.Genre" %>
<%@ page import="com.kinopoisk.model.Movie" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <title>Kinopoisk</title>
  </head>
  <body>
  <table id = "menu_table">
      <tr>
          <td>
              <button class = "menu_btn" onclick="window.location.href=('/index')">Movies</button>
              <button class = "menu_btn" onclick="window.location.href=('/actors')">Actors</button>
              <button class = "menu_btn" onclick="window.location.href=('/directors')">Directors</button>
              <button class = "menu_btn" onclick="window.location.href=('/editor')">Editor</button>
          </td>
      </tr>
  </table>

  <%!
      private GenreDAO genreDAO = new GenreDAO();
      private MovieDAO movieDAO = new MovieDAO();
  %>

  <form method="post" action="/editor_genres/edit">
      Name : <input type="text" name="name" id = "input_name" class="required">
      <br><br>
      Movies in this genre: <br>
      <table>
          <tr>
              <th></th><th>Title</th><th>Poster</th>
          </tr>
          <%
              List<Movie> movies = movieDAO.listAll();
              for (Movie movie : movies) {
          %>
          <tr>
              <td><input type="checkbox" name="movie<%=movie.getId()%>"></td>
              <td><%=movie.getTitle()%></td>
              <td><img src="<%=movie.getPosterURL()%>"></td>
          </tr>
          <%
              }
          %>
      </table>
      <br>
      <input type="submit" name = "save" value="Save" id = "save_btn"/>
      <br>
      <br>
      <h2>Delete: </h2>

      <label>
          <select name="select">
              <%
                  List<Genre> genres = genreDAO.listAll();
                  for (Genre genre : genres) {
              %>
              <option><%=genre.getId()%> <%=genre.getName()%></option>
              <%
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete" id="del_btn"></label>
  </form>
  <script src="/resources/js/simple.js"></script>
  </body>
</html>
