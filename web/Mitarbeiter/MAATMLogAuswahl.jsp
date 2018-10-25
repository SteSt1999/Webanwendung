<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ATM-Transaktionen</title>
</head>
<body>
<h1>ATM-Transaktionen</h1>
<br>Bitte wählen Sie einen bestimmten ATM, um sich seine Transaktionen anzeigen zu lassen
<br>
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <p>ATM-ID: </p>
    <input type="number" max="100" min="3" step="0.01" data-number-to-be-fixed="2" data-number-stepfactor="100"
           name="ATM-ID" size=20 maxlength=50>
    <br><br>
    <br><br>
    <input type="submit" name="AnzeigenATM" value="Anzeigen"/>
    <br>
    <br><br>
    <br><br>
    <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
    <br><br>
    <input type="submit" name="Abmelden" value="Abmelden"/>
</form>
</body>
</html>