<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ATM</title>
    </head>
    <body>
        <h1>ATM</h1>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <p>Benutzername: </p>
            <input type="text" name="LogInID" size=20 maxlength=50>
            <p>Passwort: </p>
            <input type="text" name="LogInPasswort" size=20 maxlength=50>
            <input type="submit" name="ATMLogin" value="Einloggen"/>
        </form>
    </body>
</html>