<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Auswahl Fehlgeschlagen</title>
    </head>
    <body>
        <h1>Bank</h1>
        <p>Die eingegebene Bank existiert nicht.</p>
        <p>Probieren Sie es erneut</p>
        <br>
        <form action="${pageContext.request.contextPath}/MainServlet" method="post">
            <input type="submit" name="Zurueck" value="Zurück"/>
        </form>
    </body>
</html>