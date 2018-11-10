<%@ page import="auth.AuthenticationHelper" %>
<%@ page import="auth.UserSession" %>
<%@ page import="data.sessionInformation" %>
<%@ page import="db.Role" %>
<%@ page import="db.sessionFetcher" %>
<html lang="en">
<head style="min-height: 100%;">
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/mainstyle.css">
    <style>
        .block {
            display: block;
            width: 100%;
            border: none;
            background-color: #4CAF50;
            color: white;
            padding: 14px 28px;
            font-size: 16px;
            cursor: pointer;
            text-align: center;
        }

        .block:hover {
            background-color: #ddd;
            color: black;
        }
        .block {width: 250px;
            align: middle}

        #maincontent {
            text-align: center;
        }
        .submitbutton {
            width: 30%;
            margin: auto;
        }
    </style>
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

            }
            if(sessionInfo.getStatus() == 1) {
                response.sendRedirect("/returned.jsp");
                return;
            }

            if(sessionInfo.getStatus() == 2 || sessionInfo.getStatus() == 3 ){
                response.sendRedirect("/waiting.jsp");
                return;
            }
        %>
        <p>Name of Carer: <% out.println(sessionInfo.getName()); %> </p>
        <p>Current Session: <% out.println(sessionInfo.getStartDateTime()); %> </p>

        <hr>
        <p>Please let us know that you have finished your sit, and include any comments that might be applicable below</p>

        <div class="container">
            <form action="/safe" method="POST">

                <textarea id="subject" name="subject" placeholder="Any thing to share about your sit?" style="height:100px ;width:50%"></textarea>
                <br />
                <br />
                <input type="submit" value="Confirm I have left safely" class="confirm btn block submitbutton">
            </form>

            <%
                String error = (String) session.getAttribute("leave_error_message");
                if(error != null) {
                    session.removeAttribute("leave_error_message");
            %><p style="color: red"><% out.println(error); %></p><%
            }
        %>

        </div>
    </div>
</div>

</body>
</html>