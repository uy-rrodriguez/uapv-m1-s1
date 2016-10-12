<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Architectures Distribuées, TP1</title>
</head>

<body>
    <h1>TP1 - Liste d'exercices</h1>
    <ul>
<%
    for (int i = 1; i <= 7; i++) {
        out.println("<li><a target='_blank' href='ex" + i + ".jsp'>Excercice " + i + "</a></li>");
    }
%>
    </ul>

</body>
</html>
