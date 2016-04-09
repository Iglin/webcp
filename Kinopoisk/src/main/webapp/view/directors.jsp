<%@ page import="com.kinopoisk.dao.DirectorDAO" %>
<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="com.kinopoisk.model.Director" %>
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
      <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
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

  <form action="/directors" method="post">
      Search : <input type="text" name="input_par">
      by : <select name = "option" id = "option">
      <option>Director's name</option>
      <option>Movie title</option>
      <option>Country</option>
  </select>
      <input type="submit" name = "search" value="Search"/>
  </form>
  <%
      QueryResult queryResult = null;
      if (request.getParameter("search") != null) {
          String option = request.getParameter("option");
          switch (option) {
              case "Director's name":
                  queryResult = new DirectorDAO().listByName(request.getParameter("input_par"));
                  break;
              case "Movie title":
                  queryResult = new DirectorDAO().listByMovie(request.getParameter("input_par"));
                  break;
              case "Country":
                  queryResult = new DirectorDAO().listByCountry(request.getParameter("input_par"));
                  break;
          }
      } else {
          queryResult = new DirectorDAO().listAll();
      }
      if (queryResult.isSuccess()) {
  %>
  <%
      List<Director> directors = (List<Director>) queryResult.getResult();
      for (Director director : directors) {
  %>
  <div>
      <a class="actor_name" href="/director_details/show?id=<%=director.getId()%>"><%=director.getName()%></a><br>
      <a href="/director_details/show?id=<%=director.getId()%>"><img class="dir_pic" src="<%=director.getPictureURL()%>"/></a>
  </div>
  <%
          }
      } else {
          RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
          request.setAttribute("errorMessage", queryResult.getErrorMessage());
          dispatcher.forward(request, response);
      }
  %>
  </body>
</html>
