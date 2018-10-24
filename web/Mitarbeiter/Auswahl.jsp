
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mitarbeiter</title>
</head>
<body>
<h1>Mitarbeiter</h1>
<br>Sie sind eingeloggt.
<br>
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <br><br>
    <input type="submit" name="Einzahlen" value="Auf Kundenkonto einzahlen"/>
    <br><br>
    <input type="submit" name="Abheben" value="Von Kundenkonto abheben"/>
    <br><br>
    <input type="submit" name="AllLogs" value="Bank-Transaktionen anzeigen"/>
    <br><br>
    <input type="submit" name="ATMLogs" value="ATM-Transaktionen anzeigen"/>
    <br><br>
    <input type="submit" name="UserLogs" value="Kunden-Transaktionen anzeigen"/>
    <br><br>
    <input type="submit" name="Abmelden" value="Abmelden"/>
</form>
</body>
</html>