<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Fehler</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Ihre Eingabe war leider nicht korrekt
        <br>Bitte kehren Sie zum Hauptmenü zurück
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
        </form>
    </body>
</html>