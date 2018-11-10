<%@ page import="auth.AuthenticationHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    if(AuthenticationHelper.isAuthenticated(request)) {
        response.sendRedirect("/waiting.jsp");
        return;
    }
%>

<html>
<head>
    <title>Julia's House Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">

</head>
<body>
<br/>
<br/>
<div class="container">
    <div class="row">
        <div class="col">
        </div>
        <div class=" .text-center">
            <h3>Login</h3>
        </div>
        <div class="col">
        </div>
    </div>
    </br>
    <div class="row">
        <div class="col">
        </div>
        <div class=" .text-center">
            <div class="form-group">
                <form action="/loginauth" method="POST">
                    Username <input type="text" name="username" class="form-control">
                    <p>
                        Password <input type="password" name="password" class="form-control">
                    <p>
                        <input type="submit" class="btn btn-primary loginButton" name="Login">
                </form>
            </div>
        </div>
        <div class="col">
        </div>
    </div>
    <%
        String error = (String) session.getAttribute("loginerror_message");
        if(error != null) {
            session.removeAttribute("loginerror_message");

    %>
    <p class = "error">
        <%
            out.println(error);
        %>
    </p>
    <%
        }
    %>
</div>
</body>
</html>