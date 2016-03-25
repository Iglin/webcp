<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="com.kinopoisk.dao.ActorDAO" %>
<%@ page import="com.kinopoisk.model.Actor" %>
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

  <form action="/actors" method="post">
      Search : <input type="text" name="input_par">
      by : <select name = "option" id = "option">
      <option>Actor's name</option>
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
              case "Actor's name":
                  queryResult = new ActorDAO().listByName(request.getParameter("input_par"));
                  break;
              case "Movie title":
                  queryResult = new ActorDAO().listByMovie(request.getParameter("input_par"));
                  break;
              case "Country":
                  queryResult = new ActorDAO().listByCountry(request.getParameter("input_par"));
                  break;
          }
      } else {
          queryResult = new ActorDAO().listAll();
      }
      if (queryResult.isSuccess()) {
  %>
      <%
          List<Actor> actors = (List<Actor>) queryResult.getResult();
          for (Actor actor : actors) {
      %>
      <div>
          <a href="/actor_details/show?id=<%=actor.getId()%>"><%=actor.getName()%></a><br>
          <a href="/actor_details/show?id=<%=actor.getId()%>"><img src="<%=actor.getPictureURL()%>"/></a>
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
