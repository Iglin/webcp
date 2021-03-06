<%@ page import="com.kinopoisk.dao.CountryDAO" %>
<%@ page import="com.kinopoisk.dao.DirectorDAO" %>
<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.model.Country" %>
<%@ page import="com.kinopoisk.model.Director" %>
<%@ page import="com.kinopoisk.model.Movie" %>
<%@ page import="java.util.List" %><%--
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

  <%!
      private CountryDAO countryDAO = new CountryDAO();
      private DirectorDAO directorDAO = new DirectorDAO();
      private MovieDAO movieDAO = new MovieDAO();
  %>

  <form method="post" action="/editor_directors/edit">
      <table id="table_editor">
          <tr>
              <td>
                  Name :
              </td>
              <td>
                  <input type="text" name="name" id = "input_name" class="required">
              </td>
          </tr>
          <tr>
              <td>
                  Picture URL :
              </td>
              <td>
                  <input type="text" name="pic" id = "input_pic" class="required">
              </td>
          </tr>
          <tr>
              <td>
                  Date of Birth :
              </td>
              <td>
                  <input type="date" name="dob" id = "input_date">
              </td>
          </tr>
          <tr>
              <td>
                  Country :
              </td>
              <td>
                  <select name="country" id="country_select">
                      <%
                          List<Country> countries = countryDAO.listAll();
                          for (Country country : countries) {
                      %>
                      <option id="<%=country.getId()%>">
                          <%=country.getName()%>
                      </option>
                      <%
                          }
                      %>
                  </select>
              </td>
          </tr>
      </table>

      <br><br>
      Please, choose directed by this director movies: <br>
      <table>
          <tr>
              <th></th><th>Title</th><th>Poster</th>
          </tr>
          <%
              List<Movie> movies = movieDAO.listAll();
              for (Movie movie : movies) {
          %>
          <tr>
              <td><input type="checkbox" name="movie<%=movie.getId()%>"></td>
              <td><%=movie.getTitle()%></td>
              <td><img src="<%=movie.getPosterURL()%>"></td>
          </tr>
          <%
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
                  List<Director> directors = directorDAO.listAll();
                  for (Director director : directors) {
              %>
              <option><%=director.getId()%> <%=director.getName()%></option>
              <%
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete" id="del_btn"></label>
  </form>
  <script src="/resources/js/person.js"></script>
  </body>
</html>
