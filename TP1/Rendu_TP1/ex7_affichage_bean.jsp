<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>

<jsp:useBean id="utilisateur" class="user.UserData" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Ex7 - Utilisation de Beans, Affichage</title>
</head>

<body>
    <h3>Données de l'utilisateur :</h3>

    <table>
        <tr>
            <th>Nom</th>
            <td><jsp:getProperty name="utilisateur" property="nom" /></td>
        </tr>
        <tr>
            <th>Âge</th>
            <td><jsp:getProperty name="utilisateur" property="age" /></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><jsp:getProperty name="utilisateur" property="email" /></td>
        </tr>
    </table>

    <br />
    <br />

    <a href="ex7.jsp">Retour au formulaire</a>
</body>
</html>

