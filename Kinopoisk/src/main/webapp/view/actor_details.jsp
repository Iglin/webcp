<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.kinopoisk.model.*" %>
<%@ page import="com.kinopoisk.dao.ActorDAO" %><%--
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
<h2>Actor Not Found.</h2>
<% } else {
    ActorDAO actorDAO = new ActorDAO();
    try (Session hibernateSession = actorDAO.openSession()) {
        QueryResult queryResult = actorDAO.getById(Integer.parseInt(id));
        if (queryResult.isSuccess()) {
            Actor actor = (Actor) queryResult.getResult();
%>
<h2><%=actor.getName()%></h2><br>
<img src="<%=actor.getPictureURL()%>"/><br>
<h3>Country: </h3><%=actor.getCountry().getName()%>
<h3>Date of birth: </h3><%=actor.getDateOfBirth()%>
<div>
    <h3>Movies: </h3><br>
    <table>
        <tr>
            <%
                Set<Movie> movies = actor.getMovies();
                for (Movie movie : movies) {
            %>
            <td><a href="/movie_details/show?id=<%=movie.getId()%>"><%=movie.getTitle()%></a></td>
            <% } %>
        </tr>
        <tr>
            <%
                for (Movie movie : movies) {
            %>
            <td><a href="/movie_details/show?id=<%=movie.getId()%>"><img width="100px" src="<%=movie.getPosterURL()%>"/></a></td>
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
