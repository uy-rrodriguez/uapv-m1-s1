<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ex3 - IP client</title>
</head>

<body>

<%
    /*
        3 – Afficher l'adresse IP de la machine cliente. (getRemoteAddr et getRemoteHost)
    */

    String ipClient = request.getRemoteAddr();
    String hostClient = request.getRemoteHost();

%>
    <table>
        <tr>
            <th>Client connecté</th>
            <td><%= ipClient %></td>
        </tr>
        <tr>
            <th>Nom client connecté</th>
            <td><%= hostClient %></td>
        </tr>
    </table>

</body>
</html>


