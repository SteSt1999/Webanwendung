<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Überweisung eingegangen</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Die Überweisung ist erfolgt!
        <br>
        <form action="${pageContext.request.contextPath}/OnlineBankingServlet" method="post">
            <br><br>
            <input type="submit" name="Ueberweisung" value="Weitere Überweisung"/>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>