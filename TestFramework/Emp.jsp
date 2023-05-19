<%@page import="model.Emp"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Emp> listEmp= (ArrayList<Emp>)request.getAttribute("name");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    <%
        for (int i = 0; i < listEmp.size(); i++) { %>
            <p><%out.println(listEmp.get(i).getNom() + " " + listEmp.get(i).getNom());%></p>
        <% }
    %>
</body>
</html>