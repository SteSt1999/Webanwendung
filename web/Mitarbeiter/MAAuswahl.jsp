<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Mitarbeiter</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Sie sind eingeloggt.
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Einzahlung" value="Auf Kundenkonto einzahlen"/>
            <br><br>
            <input type="submit" name="Auszahlung" value="Von Kundenkonto abheben"/>
            <br><br>
            <p> Transaktionen anzeigen: </p>
            <input type="submit" name="AllLogs" value="Bank-Transaktionen anzeigen"/>
            <br><br>
            <input type="submit" name="ATMLogs" value="ATM-Transaktionen anzeigen"/>
            <br><br>
            <input type="submit" name="UserLogs" value="Kunden-Transaktionen anzeigen"/>
            <br><br>
            <br><br>
            <input type="submit" name="KundenErstellung" value="Kunden erstellen"/>
            <br><br>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>