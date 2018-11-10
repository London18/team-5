<%@ page import="auth.AuthenticationHelper" %>
<%@ page import="auth.UserSession" %>
<%@ page import="data.sessionInformation" %>
<%@ page import="db.Role" %>
<%@ page import="db.sessionFetcher" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
%>

<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Julia's Home</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/waiting.jsp">Dashboard</a></li>
                <%
                    if(userSession.getRole() == Role.ADMIN) {
                        %><li class="active"><a href="/admin.jsp">Admin</a></li><%
                    }
                %>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logoutauth"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>

    </nav>
    <div id="maincontent">
        <%
            if(!AuthenticationHelper.isAuthenticated(request)) {
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
                response.sendRedirect("/returned.jsp");
                return;
            }
            if(sessionInfo.getStatus() == 2) {
                %><p>No sits scheduled</p><%
            }
            else if(sessionInfo.getStatus() == 3){
                SimpleDateFormat format = new SimpleDateFormat("E d MMMM 'at' hh:mm aaa");
                SimpleDateFormat format2 = new SimpleDateFormat("hh:mm aaa");
                String str = format.format(sessionInfo.getStartDateTime());
                %><p>Next Sit:</p><%
                out.println("<p>" + str + "</p>");
            }
        %>
    </div>
</div>
</body>
</html>
