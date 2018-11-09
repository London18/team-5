<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Julia's House Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">

  </head>
  <body>
  <h3>Login</h3>
  <form action="/loginauth" method="POST">
    Username <input type="text">
    <p>
      Password <input type="password">
    <p>
      <input type="submit">
  </form>
  <p>
        <a href="waiting.jsp">Waiting page</a>
  <p>
      <a href="leave.jsp">Leave page</a>
  <p>
      <a href="comments.jsp">Comments page</a>
  <p>
      <a href="returned.jsp">Returned page</a>
  </body>
</html>
