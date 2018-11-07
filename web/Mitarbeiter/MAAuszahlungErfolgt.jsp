<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Mitarbeiter</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Die Auszahlung ist erfolgt!
        <br>
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>