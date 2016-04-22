<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/view/errorPage.jsp"%>
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

  <form action="/editor" method="post">
      Edit :
      <label for="option"></label><select name = "option" id = "option">
      <option>Movies</option>
      <option>Actors</option>
      <option>Directors</option>
      <option>Genres</option>
      <option>Countries</option>
  </select>
      <input type="submit" name = "choose" value="Choose"  id="del_btn"/>
  </form>
  <%
      if (request.getParameter("choose") != null) {
          String option = request.getParameter("option");
          switch (option) {
              case "Movies":
                  response.sendRedirect("editor_movies/show");
                  break;
              case "Actors":
                  response.sendRedirect("editor_actors/show");
                  break;
              case "Directors":
                  response.sendRedirect("editor_directors/show");
                  break;
              case "Genres":
                  response.sendRedirect("editor_genres/show");
                  break;
              case "Countries":
                  response.sendRedirect("editor_countries/show");
                  break;
          }
      }
    %>
  </body>
</html>
