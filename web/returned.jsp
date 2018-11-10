<%@ page import="db.sessionFetcher" %>
<%@ page import="data.sessionInformation" %>
<%@ page import="auth.AuthenticationHelper" %><%--
  Created by IntelliJ IDEA.
  User: 06gbi
  Date: 09/11/2018
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
.btn-group{
    display: block;
}
    </style>
    <title>Returned Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">
</head>
<body>
<a href = "/logoutauth">LOGOUT</a>
<%
    if(!AuthenticationHelper.isAuthenticated(request)) {
        session.setAttribute("loginerror_message", "Please login to access that page");
        response.sendRedirect("/index.jsp");
        return;
    }
    sessionFetcher sessionFetcher = new sessionFetcher();
    sessionInformation sessionInfo = sessionFetcher.getSessionInfo(AuthenticationHelper.getSession(request).getName());
    if(sessionInfo.getStatus() == 0) {
        response.sendRedirect("/leave.jsp");
        return;
    }
    if(sessionInfo.getStatus() == 1) {
        %><p><%=sessionInfo.getName()%></p><%
        %><p><%=sessionInfo.getStartDateTime()%></p><%
    }

    if(sessionInfo.getStatus() == 2 || sessionInfo.getStatus() == 3 ){
        response.sendRedirect("/waiting.jsp");
        return;
    }
%>


<div class= "btn-group">
    <button>I am in a safe zone</button>
    </br>
    <button>Done with current session, moving on to another sit</button>


    <a href="index.jsp">To Login Page</a>
</div>

</body>
</html>
