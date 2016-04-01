<%@ page import="com.kinopoisk.dao.*" %>
<%@ page import="java.io.IOException" %><%--
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
              <!--button class = "menu_btn" onclick="window.location.href=('/search')">Advanced Search</button-->
          </td>
      </tr>
  </table>

  <%!
      private void showEditorPage(HttpServletRequest request, HttpServletResponse response, String jspURL) throws ServletException, IOException {
          RequestDispatcher dispatcher = request.getRequestDispatcher(jspURL);
          dispatcher.forward(request, response);
      }
  %>

  <form action="/editor" method="post">
      Edit :
      <label for="option"></label><select name = "option" id = "option">
      <option>Movies</option>
      <option>Actors</option>
      <option>Directors</option>
      <option>Genres</option>
      <option>Countries</option>
  </select>
      <input type="submit" name = "choose" value="Choose" id = "choose"/>
  </form>
  <%
      if (request.getParameter("choose") != null) {
          String option = request.getParameter("option");
          QueryResult queryResult;
          switch (option) {
              case "Movies":
                  queryResult = new MovieDAO().listAll();
                  request.setAttribute("queryResult", queryResult);
                  showEditorPage(request, response, "/view/editor_movies.jsp");
                  break;
              case "Actors":
                  queryResult = new ActorDAO().listAll();
                  request.setAttribute("queryResult", queryResult);
                  showEditorPage(request, response, "/view/editor_actors.jsp");
                  break;
              case "Directors":
                  queryResult = new DirectorDAO().listAll();
                  request.setAttribute("queryResult", queryResult);
                  showEditorPage(request, response, "/view/editor_directors.jsp");
                  break;
              case "Genres":
                  queryResult = new GenreDAO().listAll();
                  request.setAttribute("queryResult", queryResult);
                  showEditorPage(request, response, "/view/editor_genres.jsp");
                  break;
              case "Countries":
                  queryResult = new CountryDAO().listAll();
                  request.setAttribute("queryResult", queryResult);
                  showEditorPage(request, response, "/view/editor_countries.jsp");
                  break;
          }
      }
    %>
  </body>
</html>
