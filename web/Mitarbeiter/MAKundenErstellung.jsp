<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kunden erstellen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Hier können Sie eine Überweisung tätigen.
        <br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <p>Vorname: </p>
            <input type="text" name="Vorname" size=20 maxlength=50>
            <p>Nachname: </p>
            <input type="text" name="Nachname" size=20 maxlength=50>
            <p>Passwort: </p>
            <input type="password" name="Passwort" minlength="5" size=20 maxlength=50>
            <p>Passwort wiederholen: </p>
            <input type="password" name="PasswortWiederholung" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="KundenErstellen" value="Kunden erstellen"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>