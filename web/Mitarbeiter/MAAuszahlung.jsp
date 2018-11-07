<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Einzahlung</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Hier können Sie Geld von einem Kundenkonto abheben.
        <br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <p>Kunde: </p>
            <input type="text" name="Empfaenger" size=20 maxlength=50>
            <p>Betrag: </p>
            <input type="number" max="10000000" min="0" step="0.01" data-number-to-be-fixed="2"
                   data-number-stepfactor="100" name="Betrag" size=20 maxlength=50>
            €<br><br>
            <input type="submit" name="Abheben" value="Abheben"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>