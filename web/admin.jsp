<%--
  Created by IntelliJ IDEA.
  User: Imran
  Date: 09/11/2018
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="carerStyles.css">
    <h1 style="color:blue; text-align:center;">Julia's House </h1>
    <h2 style="color:blue; text-align:center;">Admin page</h2>
    <hr>
</head>
<body>

<table style="width:100%">
    <tr>
        <th>Carer</th>
        <th>Elapsed Time</th>
    if(userSession.getRole() != Role.ADMIN) {

    </tr>


</table>
<hr>
<a href="index.jsp">To Comments Page</a>
<a href = "/logoutauth">LOGOUT</a>
</body>
</html>
