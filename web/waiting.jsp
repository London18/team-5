<%@ page import="db.Session" %>
<%@ page import="db.SessionChecker" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: 06gbi
  Date: 09/11/2018
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waiting page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">
</head>
<body>
<a href = "/logoutauth">LOGOUT</a>
<%
    List<Session> sessions = SessionChecker.getSessionInformation();
    if(SessionChecker.isInSession(sessions)) {
        response.sendRedirect("/leave.jsp");
        return;
    }
    if(sessions.isEmpty()) {
        %>
        <p>No sits scheduled</p>
        <%
    } else {
        SimpleDateFormat format = new SimpleDateFormat("E d MMMM 'at' hh:mm aaa");
        SimpleDateFormat format2 = new SimpleDateFormat("hh:mm aaa");

        String str = format.format(new Date(sessions.get(0).getStartDate().getTime())) + " to " + format2.format(new Date(sessions.get(0).getEndDate().getTime()));
        %><p>Next Sit:</p><%
        out.println("<p>" + str + "</p>");
    }
    %>
<p>
<a href="index.jsp">To Login Page</a>

</body>
</html>
