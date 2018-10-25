<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Fehler</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Ihre Eingabe war leider nicht korrekt
        <br>Bitte kehren Sie zum Hauptmenü zurück
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
        </form>
    </body>
</html>