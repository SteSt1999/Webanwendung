<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Online-Banking</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <form action="${pageContext.request.contextPath}/OBServlet" method="post">
            <p>Benutzername: </p>
            <input type="text" name="LogInID" size=20 maxlength=50>
            <p>Passwort: </p>
            <input type="password" name="LogInPasswort" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="Login" value="Einloggen"/>
        </form>
    </body>
</html>