<%@page language="java" contentType="text/html"%>
<%@ page import="model.*"%>
<% FileUpload fileupload = (FileUpload)request.getAttribute("fichier"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AffichageEmp</title>
</head>
<body>
    <p>Byte : <%out.print(fileupload.getName());%></p>
    <p>Byte : <%out.print(fileupload.getBytesize().length);%></p>
</body>
</html>