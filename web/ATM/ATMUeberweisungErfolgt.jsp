<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Überweisung eingegangen</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Die Überweisung wird bearbeitet!
        <br>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <br><br>
            <input type="submit" name="Ueberweisung" value="Weitere Überweisung"/>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>