<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Einzahlung</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Hier Können Sie Geld abheben.
        <br>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <p>Kunde: </p>
            <input type="text" name="Empfänger" size=20 maxlength=50>
            <p>Betrag: </p>
            <input type="text" name="Betrag" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="Auszahlen" value="Abheben"/>
            <br>
        </form>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>