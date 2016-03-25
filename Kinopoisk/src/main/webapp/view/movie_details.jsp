<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.kinopoisk.model.*" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.03.2016
  Time: 14:52
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
<%
    String id = request.getParameter("id");
    if (id == null) {
%>
<h2>Movie Not Found.</h2>
<% } else {
    MovieDAO movieDAO = new MovieDAO();
    try (Session hibernateSession = movieDAO.openSession()) {
        QueryResult queryResult = movieDAO.getById(Integer.parseInt(id));
        if (queryResult.isSuccess()) {
            Movie movie = (Movie) queryResult.getResult();
%>
<h2><%=movie.getTitle()%></h2><br>
<% if (movie.getTagline() != null) { %>
<h3><%=movie.getTagline()%></h3><br>
<% } %>
<img src="<%=movie.getPosterURL()%>"/><br>
    <%=movie.getDetails()%>
<h3>Genre: </h3>
<%
    Set<Genre> genres = movie.getGenres();
    int i = 1;
    for (Genre genre : genres) {
%>
<%=genre.getName()%><% if (i < genres.size()) { %>,
<%
        }
        i++;
    }
    if (movie.getReleaseDate() != null) {
%>
<h3>Release Date: <%=movie.getReleaseDate()%></h3>
<%
    }
    if (movie.getRating() != null) {
%>
<h3>Rating: <%=movie.getRating()%></h3>
<%
    }
    if (movie.getAgeRating() != null) {
%>
<h3>Age: <%=movie.getAgeRating()%>+</h3>
<% } %>
<h3>Country: </h3>
<%
    Set<Country> countries = movie.getCountries();
    i = 1;
    for (Country country : countries) {
%>
<%=country.getName()%><% if (i < countries.size()) { %>,
<%
        }
        i++;
    }
%>
<div>
    <h3>Directors: </h3><br>

    <table>
        <tr>
            <%
                Set<Director> directors = movie.getDirectors();
                for (Director director : directors) {
            %>
            <td><a href="/director_details/show?id=<%=director.getId()%>"><%=director.getName()%></a></td>
            <% } %>
        </tr>
        <tr>
            <%
                for (Director director : directors) {
            %>
            <td><a href="/director_details/show?id=<%=director.getId()%>"><img width="100px" src="<%=director.getPictureURL()%>"/></a></td>
            <% } %>
        </tr>
    </table>
</div>
<div>
    <h3>Actors: </h3><br>

    <table>
        <tr>
    <%
        Set<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
    %>
            <td><a href="/actor_details/show?id=<%=actor.getId()%>"><%=actor.getName()%></a></td>
<% } %>
        </tr>
        <tr>
            <%
                for (Actor actor : actors) {
            %>
            <td><a href="/actor_details/show?id=<%=actor.getId()%>"><img width="100px" src="<%=actor.getPictureURL()%>"/></a></td>
            <% } %>
        </tr>
    </table>
</div>

<%
    } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
        request.setAttribute("errorMessage", queryResult.getErrorMessage());
        dispatcher.forward(request, response);
    }
    } catch (ExceptionInInitializerError e) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
        request.setAttribute("errorMessage", "Could not set database session.");
        dispatcher.forward(request, response);
    }
}
%>
</body>
</html>
