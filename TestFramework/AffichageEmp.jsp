<%@page language="java" contentType="text/html"%>
<%@ page import="model.*"%>
<% Emp emp = (Emp)request.getAttribute("emp"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AffichageEmp</title>
</head>
<body>
    <p>Nom : <%out.print(emp.getNom());%></p>
    <p>Prenom : <%out.print(emp.getPrenom());%></p>
</body>
</html>