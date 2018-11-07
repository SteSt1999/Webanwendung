<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>ATM-Transaktionen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Bitte wählen Sie einen bestimmten ATM, um sich seine Transaktionen anzeigen zu lassen
        <br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <p>ATM-ID: </p>
            <input type="number" min="3" step="1" name="ATM-ID" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="AnzeigenATM" value="Anzeigen"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>