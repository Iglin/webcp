<%@ page import="com.kinopoisk.dao.QueryResult" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.kinopoisk.model.*" %>
<%@ page import="com.kinopoisk.dao.DirectorDAO" %><%--
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
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<table id = "menu_table">
    <tr>
        <td>
            <button class = "menu_btn" onclick="window.location.href=('/index')">Movies</button>
            <button class = "menu_btn" onclick="window.location.href=('/actors')">Actors</button>
            <button class = "menu_btn" onclick="window.location.href=('/directors')">Directors</button>
            <!--button class = "menu_btn" onclick="window.location.href=('/search')">Advanced Search</button-->
        </td>
    </tr>
</table>
<%
    String id = request.getParameter("id");
    if (id == null) {
%>
<h2>Director Not Found.</h2>
<% } else {
    DirectorDAO directorDAO = new DirectorDAO();
    try (Session hibernateSession = directorDAO.openSession()) {
        QueryResult queryResult = directorDAO.getById(Integer.parseInt(id));
        if (queryResult.isSuccess()) {
            Director director = (Director) queryResult.getResult();
%>
<h2><%=director.getName()%></h2>
<div><img class="dir_pic" src="<%=director.getPictureURL()%>"/></div>
<h3>Country: </h3><%=director.getCountry().getName()%>
<% if (director.getDateOfBirth()!= null) { %>
<h3>Date of birth: </h3><%=director.getDateOfBirth()%>
<% } %>
    <h3>Movies: </h3>
    <table id="res_table">
        <tr>
            <%
                Set<Movie> movies = director.getMovies();
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
