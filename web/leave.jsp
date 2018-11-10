<%@ page import="db.sessionFetcher" %>
<%@ page import="data.sessionInformation" %>
<%@ page import="auth.AuthenticationHelper" %>
<%@ page import="auth.UserSession" %><%--
  Created by IntelliJ IDEA.
  User: 06gbi
  Date: 09/11/2018
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">

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
    </style>
</head>
<body>
<%
    if(!AuthenticationHelper.isAuthenticated(request)) {
        session.setAttribute("loginerror_message", "Please login to access that page");
        response.sendRedirect("/index.jsp");
        return;
    }
    sessionFetcher sessionFetcher = new sessionFetcher();
    UserSession userSession = AuthenticationHelper.getSession(request);
    sessionInformation sessionInfo = sessionFetcher.getSessionInfo(userSession.getName());
    if(sessionInfo.getStatus() == 0) {
        %><p><%=sessionInfo.getName()%></p><%
        %><p><%=sessionInfo.getStartDateTime()%></p><%
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
<h1 style="color:blue; text-align:center;"> Julia's House</h1>
<a href = "/logoutauth">LOGOUT</a>

<p>Name of Carer:  </p>
<p>Current Session: </p>

<hr>

<button id="button1" class="block">I have left safely</button>
<hr>
<div class="container">
    <form action="">

        <label for="subject">Add comments</label>
        <textarea id="subject" name="subject" placeholder="Any thing to share about your sit?" style="height:100px ;width:50%"></textarea>

        <input type="submit" value="Submit">

    </form>

    <button class="confirm btn">Confirm and Send</button>
</div>


<a href="index.jsp">To Login Page</a>
</body>
</html>
