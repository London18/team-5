<%@ page import="auth.AuthenticationHelper" %>
<%@ page import="auth.UserSession" %>
<%@ page import="db.GetNursesNonResponsive" %>
<%@ page import="db.Role" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head style="min-height: 100%;">
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/mainstyle.css">
</head>
<body>

<%
    UserSession userSession = AuthenticationHelper.getSession(request);
    if(userSession == null) {
        request.getSession().setAttribute("loginerror_message", "Please login to access that page");
        response.sendRedirect("/index.jsp");
        return;
    }
    if(userSession.getRole() != Role.ADMIN) {
        request.getSession().setAttribute("message_message", "You don't have permissions to view that page");
        response.sendRedirect("/message.jsp");
        return;
    }
%>

<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Julia's Home</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/waiting.jsp">Dashboard</a></li>
                <li class="active"><a href="/admin.jsp">Admin</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logoutauth"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>

    </nav>

    <div id="maincontent">
        <p> Below is a list of users who have not confirmed they have returned home within the alloted time frame: </p>
        <table border="1">
            <tr><td>Name</td><td>Elapsed Time</td></tr>
            <%
            // Get results then for loop
            List<String> results = GetNursesNonResponsive.getNonResponses();
            for(int i = 0; i < results.size();i+=2){
                out.println("<tr><td>" + results.get(i) + "</td>");
                out.println("<td>" + results.get(i + 1) + "</td></tr>");
            }
        %>
    </div>
</div>
</body>
</html>