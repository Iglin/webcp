<%@ page import="com.kinopoisk.dao.*" %>
<%@ page import="com.kinopoisk.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.kinopoisk.controller.MainController" %>
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

      private MainController mainController = MainController.getInstance();
  %>

  <form method="post" action="/editor_genres/edit">
      Name : <input type="text" name="name" id = "input_title" class="required">
      <br><br>
      Movies in this genre: <br>
      <table>
          <tr>
              <th></th><th>Title</th><th>Poster</th>
          </tr>
          <%
              QueryResult queryResult = movieDAO.listAll();
              if (queryResult.isSuccess()) {
                  List<Movie> movies = (List<Movie>) queryResult.getResult();
                  for (Movie movie : movies) {
          %>
          <tr>
              <td><input type="checkbox" name="movie<%=movie.getId()%>"></td>
              <td><%=movie.getTitle()%></td>
              <td><img src="<%=movie.getPosterURL()%>"></td>
          </tr>
          <%
                  }
              } else {
                  mainController.showErrorPage(request, response, queryResult.getErrorMessage());
              }
          %>
      </table>
      <br>
      <input type="submit" name = "save" value="Save" id = "save_btn"/>
      <br>

      <label>
          <select name="select">
              <%
                  queryResult = genreDAO.listAll();
                  if (queryResult.isSuccess()) {
                      List<Genre> genres = (List<Genre>) queryResult.getResult();
                      for (Genre genre : genres) {
              %>
              <option><%=genre.getId()%> <%=genre.getName()%></option>
              <%
                      }
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete"></label>
  </form>
  <script src="/resources/js/movies.js"></script>
  </body>
</html>
