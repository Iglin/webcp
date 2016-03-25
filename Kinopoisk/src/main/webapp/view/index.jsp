<%@ page import="com.kinopoisk.dao.QueryResult" %>
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
    <title>Kinopoisk</title>
  </head>
  <body>
  <table>
      <tr>
          <td>
              <button class = "menu_btn" onclick="window.location.href=('/index')">Movies</button>
              <button class = "menu_btn" onclick="window.location.href=('/actors')">Actors</button>
              <button class = "menu_btn" onclick="window.location.href=('/directors')">Directors</button>
              <button class = "menu_btn" onclick="window.location.href=('/search')">Advanced Search</button>
          </td>
      </tr>
  </table>

  <form action="/index" method="post">
      Search : <input type="text" name="input_par">
      by : <select name = "option" id = "option">
      <option>Movie title</option>
      <option>Actor</option>
      <option>Director</option>
      <option>Genre</option>
      <option>Country</option>
  </select>
      <input type="submit" name = "search" value="Search"/>
  </form>
  <%
      QueryResult queryResult = null;
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
      if (queryResult.isSuccess()) {
  %>
  <table>
      <tr><th>Title</th><th>Poster</th><th>Description</th></tr>
  <%
          List<Movie> movies = (List<Movie>) queryResult.getResult();
          for (Movie movie : movies) {
  %>
      <tr>
          <td><a href="/movie_details/show?id=<%=movie.getId()%>"><%=movie.getTitle()%></a></td>
          <td><a href="/movie_details/show?id=<%=movie.getId()%>"><img src="<%=movie.getPosterURL()%>"/></a></td>
          <td><a href="/movie_details/show?id=<%=movie.getId()%>"><%=movie.getDetails()%></a></td>
      </tr>
      <%
              }
      } else {
                  RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
                  request.setAttribute("errorMessage", queryResult.getErrorMessage());
                  dispatcher.forward(request, response);
      }
  %>
  </table>
  </body>
</html>
