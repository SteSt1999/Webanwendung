<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login fehlgeschlagen</title>
</head>
<body>
<h1>Mitarbeiter</h1>
<br>Sie sind nicht eingeloggt.
<br>Bitte melden Sie sich erneut an.
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <input type="submit" name="Abmelden" value="Zum Start"/>
</form>
</body>
</html>