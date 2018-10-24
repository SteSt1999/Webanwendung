<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ATM-Transaktionen</title>
</head>
<body>
<h1>ATM-Transaktionen</h1>
<br>Hier sehen Sie die Transaktionen des ATMs.
<br>
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <br><br>
    <br><br>
    <br><br>
    <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
    <br><br>
    <input type="submit" name="Abmelden" value="Abmelden"/>
</form>
</body>
</html>