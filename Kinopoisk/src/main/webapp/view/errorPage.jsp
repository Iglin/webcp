<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<h3>Error</h3>
<%
    if (exception instanceof NoClassDefFoundError) {
%>
<%="Could not connect to database."%>
<%
    } else {
%>
<% if (exception.getLocalizedMessage() != null) { %>
<%=exception.getLocalizedMessage()%>
<% } else if (exception.getMessage() != null) { %>
<%=exception.getMessage()%>
<% } else { %>
Ошибка!
<% }
}%>
<a href="/view/index.jsp">Main page</a>
</body>
</html>
