<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.02.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="/view/errorPage.jsp"%>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<h3>Error</h3>
<c:out value="${errorMessage}"/>
<a href="/view/index.jsp">Main page</a>
</body>
</html>
