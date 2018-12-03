<%@ page import="Logik.Umwandlung" %>
<%@ page import="Logik.Verwaltung.Kunde" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>ATM</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Sie sind eingeloggt.
        <br>
        <br>Kontostand:
        <%
            Kunde kunde = (Kunde) session.getAttribute("kunde");
            out.println(Umwandlung.centToEuroString(kunde.getKontostand()));
        %>
        <br>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <br><br>
            <input type="submit" name="Ueberweisung" value="Überweisen"/>
            <br><br>
            <input type="submit" name="Transactions" value="Kontoverlauf"/>
            <br><br>
            <input type="submit" name="Einzahlung" value="Einzahlen"/>
            <br><br>
            <input type="submit" name="Auszahlung" value="Abheben"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>