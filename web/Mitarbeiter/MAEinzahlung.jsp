<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Einzahlung</title>
    </head>
    <body>
        <h1>Mitarbeiter-Konto</h1>
        <br>Hier können Sie Geld auf ein Kundenkonto einzahlen.
        <br>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <p>Kunde: </p>
            <input type="text" name="Empfänger" size=20 maxlength=50>
            <p>Betrag: </p>
            <input type="text" name="Betrag" size=20 maxlength=50>
            €<br><br>
            <input type="submit" name="Einzahlen" value="Einzahlen"/>
            <br>
        </form>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>