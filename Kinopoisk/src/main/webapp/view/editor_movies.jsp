<%@ page import="com.kinopoisk.dao.*" %>
<%@ page import="com.kinopoisk.model.*" %>
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
      private GenreDAO genreDAO = new GenreDAO();
      private CountryDAO countryDAO = new CountryDAO();
      private ActorDAO actorDAO = new ActorDAO();
      private DirectorDAO directorDAO = new DirectorDAO();
      private MovieDAO movieDAO = new MovieDAO();
  %>

  <form method="post" action="/editor_movies/edit">
      <table id="table_editor">
          <tr>
              <td>
                  Title :
              </td>
              <td>
                  <input type="text" name="title" id = "input_title" class="required">
              </td>
          </tr>
          <tr>
              <td>
                  Tagline :
              </td>
              <td>
                  <input type="text" name="tagline" id = "input_tagline">
              </td>
          </tr>
          <tr>
              <td>
                  Poster URL :
              </td>
              <td>
                  <input type="text" name="poster" id = "input_poster" class="required">
              </td>
          </tr>
          <tr>
              <td>
                  Release Date :
              </td>
              <td>
                  <input type="date" name="date" id = "input_date">
              </td>
          </tr>
          <tr>
              <td>
                  Rating :
              </td>
              <td>
                  <input type="number" step="0.1" min="0" max="10" name="rating" id = "input_rating">
              </td>
          </tr>
          <tr>
              <td>
                  Age Rating :
              </td>
              <td>
                  <input type="number" min="0" max="18" name="age" id = "input_age">+
              </td>
          </tr>
          <tr>
              <td>
                  Duration (minutes) :
              </td>
              <td>
                  <input type="number" min="1" max="500" name="duration" id = "input_duration">
              </td>
          </tr>
      </table>
      Description : <br>
      <textarea id="text_descr" name="description" cols="40" rows="5" style="resize: none" class="required"></textarea> <br>

      <br>
      Please, choose countries for this movie: <br>
      <table>
          <%
              List<Country> countries = countryDAO.listAll();
              for (Country country : countries) {
          %>
          <tr>
              <td><input type="checkbox" name="country<%=country.getId()%>"></td>
              <td><%=country.getName()%></td>
          </tr>
          <%
              }
          %>
      </table>
      <br>
      Please, choose genres for this movie: <br>
      <table>
          <%
              List<Genre> genres = genreDAO.listAll();
              for (Genre genre : genres) {
          %>
          <tr>
              <td><input type="checkbox" name="genre<%=genre.getId()%>"></td>
              <td><%=genre.getName()%></td>
          </tr>
          <%
              }
          %>
      </table>
      <br>
      Please, choose actors for this movie: <br>
      <table>
          <tr>
              <th></th><th>Name</th><th>Photo</th>
          </tr>
          <%
              List<Actor> actors = actorDAO.listAll();
              for (Actor actor : actors) {
          %>
          <tr>
              <td><input type="checkbox" name="actor<%=actor.getId()%>"></td>
              <td><%=actor.getName()%></td>
              <td><img src="<%=actor.getPictureURL()%>"></td>
          </tr>
          <%
              }
          %>
      </table>
      <br>
      Please, choose directors for this movie: <br>
      <table>
          <tr>
              <th></th><th>Name</th><th>Photo</th>
          </tr>
          <%
              List<Director> directors = directorDAO.listAll();
              for (Director director : directors) {
          %>
          <tr>
              <td><input type="checkbox" name="director<%=director.getId()%>"></td>
              <td><%=director.getName()%></td>
              <td><img src="<%=director.getPictureURL()%>"></td>
          </tr>
          <%
              }
          %>
      </table>
      <input type="submit" name = "save" value="Save" id = "save_btn"/>
      <br>
      <br>
      <h2>Delete: </h2>
      <label>
          <select name="select">
              <%
                  List<Movie> movies = movieDAO.listAll();
                  for (Movie movie : movies) {
              %>
              <option><%=movie.getId()%> <%=movie.getTitle()%></option>
              <%
                  }
              %>
          </select>
      </label>
      <label><input type="submit" name="delete" value="Delete" id="del_btn"></label>
  </form>
  <script src="/resources/js/movies.js"></script>
  </body>
</html>
