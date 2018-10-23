<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>ATM</h1>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <p>Benutzername: </p>
            <input type="text" name="LogInID" size=20 maxlength=50>
            <p>Passwort: </p>
            <input type="text" name="LogInPasswort" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="Login" value="Einloggen"/>
        </form>
    </body>
</html>