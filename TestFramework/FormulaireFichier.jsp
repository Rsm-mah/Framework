<%@page language="java" contentType="text/html"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fichier</title>
</head>
<body>
    <form action="file" method="post" enctype="multipart/form-data">
        <p>Fichier : <input type="file" name="fichier"></p>
        <input type="submit" value="Valider">
    </form>
</body>
</html>