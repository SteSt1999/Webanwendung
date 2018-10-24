<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Online-Banking</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Sie sind eingeloggt.
        <br>
        <br>Kontostand: unbekannt
        <br>
        <form action="${pageContext.request.contextPath}/OnlineBankingServlet" method="post">
            <br><br>
            <input type="submit" name="Ueberweisung" value="Überweisen"/>
            <br><br>
            <input type="submit" name="Transactions" value="Kontoverlauf"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>