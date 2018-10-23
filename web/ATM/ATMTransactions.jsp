<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Kontoverlauf</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Hier sehen Sie die Transaktionen der letzten x Tage.
        <br>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>