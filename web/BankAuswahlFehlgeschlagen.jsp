<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Online-Banking</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <p>Die eingegebene Bank existiert nicht.</p>
        <p>Probieren Sie es erneut</p>
        <br>
        <form action="${pageContext.request.contextPath}/MainServlet" method="post">
            <input type="submit" name="Zurueck" value="Zurück"/>
        </form>
    </body>
</html>