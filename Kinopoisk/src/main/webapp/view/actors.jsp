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
  <table>
      <tr>
          <td>
              <button class = "menu_btn" onclick="window.location.href=('/movies/')">Movies</button>
              <button class = "menu_btn" onclick="window.location.href=('/actors/')">Actors</button>
              <button class = "menu_btn" onclick="window.location.href=('/directors/')">Directors</button>
              <button class = "menu_btn" onclick="window.location.href=('/search/')">Advanced Search</button>
          </td>
      </tr>
  </table>

  <form action="/actors/" method="post">
      Search : <input type="text" name="input_par">
      by : <select name = "option" id = "option">
      <option>Actor's name</option>
      <option>Movie title</option>
      <option>Country</option>
  </select>
      <input type="submit" name = "search" value="Search"/>
  </form>
  <c:forEach var="actor" items="${actors}">
      <div>
          <a href="/actor?id=${actor.getId()}">${actor.getName()}</a><br>
          <c:if test="${not empty actor.pictureURL}">
              <img src="${actor.getPictureURL()}"/><br>
          </c:if>
      </div>
  </c:forEach>
  </body>
</html>
