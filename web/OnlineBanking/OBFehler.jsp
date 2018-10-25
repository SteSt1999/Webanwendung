<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Fehler</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Ihre Eingabe war leider nicht korrekt
        <br>Bitte kehren Sie zum Hauptmenü zurück
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
        </form>
    </body>
</html>