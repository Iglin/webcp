<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.model.Movie" %>
<%@ page import="java.util.List" %><%--
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

  <form action="/index" method="post">
      Search : <input type="text" name="input_par" id = "input_par">
      by : <select name = "option" id = "option">
      <option>Movie title</option>
      <option>Actor</option>
      <option>Director</option>
      <option>Genre</option>
      <option>Country</option>
  </select>
      <input type="submit" name = "search" value="Search" id = "search"/>
  </form>
  <%
      List<Movie> queryResult = null;
      if (request.getParameter("search") != null) {
          String option = request.getParameter("option");
          switch (option) {
              case "Actor":
                  queryResult = new MovieDAO().listByActor(request.getParameter("input_par"));
                  break;
              case "Movie title":
                  queryResult = new MovieDAO().listByTitle(request.getParameter("input_par"));
                  break;
              case "Director":
                  queryResult = new MovieDAO().listByDirector(request.getParameter("input_par"));
                  break;
              case "Genre":
                  queryResult = new MovieDAO().listByGenre(request.getParameter("input_par"));
                  break;
              case "Country":
                  queryResult = new MovieDAO().listByCountry(request.getParameter("input_par"));
                  break;
          }
      } else {
          queryResult = new MovieDAO().listAll();
      }
  %>
  <table id = "res_table">
      <tr><th class="movie_title">Title</th><th>Poster</th><th>Description</th></tr>
  <%
          for (Movie movie : queryResult) {
  %>
      <tr>
          <td class="movie_title"><a href="/movie_details/show?id=<%=movie.getId()%>"><%=movie.getTitle()%></a></td>
          <td><a href="/movie_details/show?id=<%=movie.getId()%>"><img src="<%=movie.getPosterURL()%>"/></a></td>
          <td class="descr"><%=movie.getDetails()%></td>
      </tr>
      <%
          }
      %>
  </table>
  </body>
</html>
