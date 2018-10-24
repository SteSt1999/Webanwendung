<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Einzahlung</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Hier können Sie Geld abheben.
        <br>
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <p>Betrag: </p>
            <input type="number" max="10000000" min="0" step="0.01" data-number-to-be-fixed="2"
                   data-number-stepfactor="100" name="Betrag" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="Auszahlen" value="Abheben"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>