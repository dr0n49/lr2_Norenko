<%@ page import="ErrorApp2.DBHandler" %>
<%@ page import="ErrorApp2.ErrErrors" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find errErrors by author</title>
</head>
<body>
<%
    String date = request.getParameter("date");
    List<errErrors> errErrors = new ArrayList<errErrors>();
    DBHandler dbHandler;
    try {
        dbHandler = new DBHandler();
        orgEvents = dbHandler.findEventByDate(date);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    pageContext.setAttribute("errErrors", errErrors);
%>
<table>
    <tr><th>Name</th><th>Date</th><th>Description</th><th>ErrorApp2.ErrorsType ID</th></tr>
    <c:forEach var="errErrors" items="${errErrors}">
        <tr><td>${errErrors.name}</td>
            <td>${errErrors.date}</td>
            <td>${errErrors.description}</td>
            <td>${errErrors.typeID}</td>
            </tr>
    </c:forEach>
</table>
<p><a href="index.jsp">Back</a></p>
</body>
</html>
