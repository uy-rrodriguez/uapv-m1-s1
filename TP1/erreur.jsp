<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page isErrorPage=true %>

<html>
<head>
    <title>Page d'erreur</title>
</head>

<body>
    <h3>Il y a eu une erreur dans la page JSP !!!</h3>
    <%= exception.getMessage(); %>
</body>
</html>

