<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ex5 - Page d'erreur</title>
</head>

<body>
    <h3>Erreur :</h3>
    <%= exception.getMessage() %>


    <h3>StackTrace :</h3>
    <pre>
        <% exception.printStackTrace(new java.io.PrintWriter(out)); %>
    </pre>

</body>
</html>


