<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login fehlgeschlagen</title>
</head>
<body>
<h1>Mitarbeiter</h1>
<br>Der Login war nicht erfolgreich.
<br>Bitte probieren Sie es erneut.
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <input type="submit" name="LoginFehlgeschlagenZurueck" value="Zurück"/>
</form>
</body>
</html>