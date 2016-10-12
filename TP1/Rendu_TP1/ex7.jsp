<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    /*
     *   7 – Utilisation de beans
     *       Ecrire une classe UserData d’un package user, contenant des informations sur un utilisateur : nom,
     *       âge et mail. La classe compilée (avec son package) sera placée dans le répertoire classes de WEB-INF.
     *       Ecrire ensuite une première page jsp qui, dans un formulaire, demande à un utilisateur de saisir ses
     *       informations. Les conserver ensuite dans un bean UserData (dans une seconde page jsp). Permettre
     *       enfin d’enchaîner (par un lien) sur une dernière page pour les réafficher. (C2 transparent 24)
     */
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ex7 - Utilisation de Beans, Formulaire</title>
    <style>
        input {
            min-width: 300px;
        }
    </style>
</head>

<body>
    <h3>Insérez les données de l'utilisateur</h3>

    <form action="ex7_creation_bean.jsp" method="post">
        <table>
            <tr>
                <th>Nom</th>
                <td><input type="text" name="nom" value="Le Test" /></td>
            </tr>
            <tr>
                <th>Âge</th>
                <td><input type="text" name="age" value="25" /></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><input type="text" name="email" value="letest@univ-avignon.fr" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Sauvegarder" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
