<%@ page import="com.kinopoisk.controller.MainController" %>
<%@ page import="com.kinopoisk.dao.MovieDAO" %>
<%@ page import="com.kinopoisk.model.*" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.03.2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/view/errorPage.jsp"%>
<%!
    MainController mainController = MainController.getInstance();
%>
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
<%
    String id = request.getParameter("id");
    if (id == null) {
%>
<h2>Movie Not Found.</h2>
<% } else {
    MovieDAO movieDAO = new MovieDAO();
    try (Session hibernateSession = movieDAO.openSession()) {
        Optional<Movie> queryResult = movieDAO.getById(Integer.parseInt(id));
        if (queryResult.isPresent()) {
            Movie movie = (Movie) queryResult.get();
%>
<h2><%=movie.getTitle()%></h2>
<% if (movie.getTagline() != null) { %>
<h3><%=movie.getTagline()%></h3>
<% } %>
<div><img class="dir_pic" src="<%=movie.getPosterURL()%>"/></div>
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
    <h3>Directors: </h3><br>

    <table id = "dir_table">
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
    <h3>Actors: </h3><br>

    <table id="res_table">
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
            <td><a href="/actor_details/show?id=<%=actor.getId()%>"><img width="100px" height="270px" src="<%=actor.getPictureURL()%>"/></a></td>
            <% } %>
        </tr>
    </table>

<%
    } else {
        mainController.showErrorPage(request, response, "Could not load movie from database.");
    }
    } catch (Exception e) {
        mainController.showErrorPage(request, response, "Could not set database session.");
    }
}
%>
</body>
</html>
