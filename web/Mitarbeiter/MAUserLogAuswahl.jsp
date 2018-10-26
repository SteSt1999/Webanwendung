<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kunden-Transaktionen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Bitte wählen Sie einen bestimmten Kunden, um sich seine Transaktionen anzeigen zu lassen
        <br>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <p>Kunden-Benutzername:</p>
            <input type="text" name="Empfaenger" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="AnzeigenUser" value="Anzeigen"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>