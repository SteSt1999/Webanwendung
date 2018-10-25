<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login fehlgeschlagen</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Der Login war nicht erfolgreich.
        <br>Bitte probieren Sie es erneut.
        <form action="${pageContext.request.contextPath}/OBServlet" method="post">
            <input type="submit" name="LoginFehlgeschlagenZurueck" value="Zurück"/>
        </form>
    </body>
</html>