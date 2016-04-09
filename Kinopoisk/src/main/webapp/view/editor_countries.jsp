<%@ page import="com.kinopoisk.controller.MainController" %>
<%@ page import="com.kinopoisk.dao.CountryDAO" %>
<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="com.kinopoisk.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kinopoisk.model.Country" %>
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
      private MovieDAO movieDAO = new MovieDAO();

      private MainController mainController = MainController.getInstance();
  %>

  <form method="post" action="/editor_countries/edit">
      Name : <input type="text" name="name" id = "input_name" class="required">
      <br><br>
      Filmed in this country: <br>
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
      <br>
      <h2>Delete: </h2>

      <label>
          <select name="select">
              <%
                  queryResult = countryDAO.listAll();
                  if (queryResult.isSuccess()) {
                      List<Country> countries = (List<Country>) queryResult.getResult();
                      for (Country country : countries) {
              %>
              <option><%=country.getId()%> <%=country.getName()%></option>
              <%
                      }
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete" id="del_btn"></label>
  </form>
  <script src="/resources/js/simple.js"></script>
  </body>
</html>
