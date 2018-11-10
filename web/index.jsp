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
        <div class="row">
            <form action="/loginauth" method="POST">
                Username <input type="text" name="username">
                <p>
                    Password <input type="password" name="password">
                <p>
                    <input type="submit">
            </form>
        </div>
        <%
            String error = (String) session.getAttribute("loginerror_message");
            if(error != null) {
                session.removeAttribute("loginerror_message");

        %>
        <p style="color:red">
            <%
                out.println(error);
            %>
        </p>
        <%
            }
        %>

        <p>
            <a href="waiting.jsp">Waiting page</a>
        <p>
            <a href="leave.jsp">Leave page</a>
        <p>
            <a href="returned.jsp">Returned page</a>
    </div>
  </body>
</html>
