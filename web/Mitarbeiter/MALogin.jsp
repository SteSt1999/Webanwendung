<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Mitarbeiter</h1>
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <p>Benutzername: </p>
    <input type="text" name="LogInID" size=20 maxlength=50>
    <p>Passwort: </p>
    <input type="password" name="LogInPasswort" size=20 maxlength=50>
    <input type="submit" name="Login" value="Einloggen"/>
</form>
</body>