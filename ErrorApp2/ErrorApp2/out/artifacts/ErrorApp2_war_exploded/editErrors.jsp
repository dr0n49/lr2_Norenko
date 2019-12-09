<%@ page import="ErrorApp2.OrgEvent" %>
<%@ page import="ErrorApp2.DBHandler" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit errors</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("errorsID").toString());
    try {
        DBHandler dbHandler = new DBHandler();
        ErrErrors errErrors = dbHandler.findEventByID(id);
        if(orgEvent != null)
        {
            pageContext.setAttribute("errErrors", errErrors);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<h3>Edit errErrors</h3>
<form method="post" action="editErrorsS?action=submit">
    <input type="hidden" value="${errErrors.ID}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${errErrors.name}"/><br><br>
    <label>Date</label><br>
    <input name="date" value="${errErrors.date}"/><br><br>
    <label>Description</label><br>
    <input name="desc" value="${errErrors.description}"/><br><br>
    <label>TypeID</label><br>
    <input name="typeID" type="number" value="${errErrors.typeID}"/><br><br>
    <button type="submit">Save</button>
</form>
<p><a href="index.jsp">Back</a></p>
</body>
</html>
