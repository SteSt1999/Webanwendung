<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ATM</title>
    </head>
    <body>
        <h1>ATM</h1>
        <br>Der Login war nicht erfolgreich.
        <br>Bitte probieren Sie es erneut.
        <form action="${pageContext.request.contextPath}/ATMServlet" method="post">
            <input type="submit" name="ATMLoginFehlgeschlagenZurueck" value="Zurück"/>
        </form>
    </body>
</html>