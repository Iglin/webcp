<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Kinopoisk</title>
  </head>
  <body>
  <form action="MovieController" method="post">
      Search : <input type="text" name="input_par">
      by : <select name = "option" id = "option">
      <option>Movie title</option>
      <option>Actor</option>
      <option>Director</option>
      <option>Genre</option>
      <option>Country</option>
  </select>
      <input type="submit" name = "search" value="Search"/>
  </form>
  <c:forEach var="movie" items="${movies}">
      <div>
          <a href="/movie?id=${movie.getId()}">${movie.getTitle()}</a><br>
          <img src="${movie.getPosterURL()}"/><br>
      </div>
  </c:forEach>
  </body>
</html>
