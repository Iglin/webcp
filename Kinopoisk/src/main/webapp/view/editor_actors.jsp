<%@ page import="com.kinopoisk.dao.ActorDAO" %>
<%@ page import="com.kinopoisk.dao.CountryDAO" %>
<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="com.kinopoisk.model.Actor" %>
<%@ page import="com.kinopoisk.model.Country" %>
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

  <%!
      private CountryDAO countryDAO = new CountryDAO();
      private ActorDAO actorDAO = new ActorDAO();
      private MovieDAO movieDAO = new MovieDAO();

      private void showErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
          RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
          request.setAttribute("errorMessage", errorMessage);
          try {
              dispatcher.forward(request, response);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  %>

  <form method="post" action="/editor_actors/edit">
      Name : <input type="text" name="name" id = "input_name" class="required"> <br>
      Picture URL : <input type="text" name="pic" id = "input_pic" class="required"> <br>
      Date of Birth : <input type="date" name="dob" id = "input_date"> <br>
      <br>
      Country : <br>
      <select name="country">
          <%
              QueryResult queryResult = countryDAO.listAll();
              if (queryResult.isSuccess()) {
                  List<Country> countries = (List<Country>) queryResult.getResult();
                  for (Country country : countries) {
          %>
          <option id="<%=country.getId()%>">
              <%=country.getName()%>
          </option>
          <%
                  }
              } else {
                  showErrorPage(request, response, queryResult.getErrorMessage());
              }
          %>
      </select>
      <br><br>
      Please, choose movies with this actor: <br>
      <table>
          <tr>
              <th></th><th>Title</th><th>Poster</th>
          </tr>
          <%
              queryResult = movieDAO.listAll();
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
                  showErrorPage(request, response, queryResult.getErrorMessage());
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
                  queryResult = actorDAO.listAll();
                  if (queryResult.isSuccess()) {
                      List<Actor> allActors = (List<Actor>) queryResult.getResult();
                      for (Actor actor : allActors) {
              %>
              <option><%=actor.getId()%> <%=actor.getName()%></option>
              <%
                      }
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete" id="del_btn"></label>
  </form>
  <script src="/resources/js/person.js"></script>
  </body>
</html>
