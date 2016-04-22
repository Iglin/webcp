<%@ page import="com.kinopoisk.dao.ActorDAO" %>
<%@ page import="com.kinopoisk.model.Actor" %>
<%@ page import="java.util.List" %>
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
      List<Actor> actors = null;
      if (request.getParameter("search") != null) {
          String option = request.getParameter("option");
          switch (option) {
              case "Actor's name":
                  actors = new ActorDAO().listByName(request.getParameter("input_par"));
                  break;
              case "Movie title":
                  actors = new ActorDAO().listByMovie(request.getParameter("input_par"));
                  break;
              case "Country":
                  actors = new ActorDAO().listByCountry(request.getParameter("input_par"));
                  break;
          }
      } else {
          actors = new ActorDAO().listAll();
      }
  %>
  <table>
      <%
          int i = 0;
          for (Actor actor : actors) {
              if (i == 0) {
      %>
      <tr>
      <%
              }
      %>
      <td>
          <a class="actor_name" href="/actor_details/show?id=<%=actor.getId()%>"><%=actor.getName()%></a><br>
          <a href="/actor_details/show?id=<%=actor.getId()%>"><img class="actor_pic" src="<%=actor.getPictureURL()%>"/></a>
      </td>
      <%    if (i == 3) {    %>
          </tr>
      <%
       i = 0;
      }
      i++;
              }
      %>
  </table>
  </body>
</html>
