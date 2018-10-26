<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Auswahlmenü</title>
    </head>
    <body>
        <h1>Auswahlmenü</h1>
        <form action="${pageContext.request.contextPath}/MainServlet" method="post">
            <br><br>
            <input type="submit" name="ATM" value="ATM"/>
            <br><br>
            <input type="submit" name="OnlineBanking" value="Online-Banking"/>
            <br><br>
            <input type="submit" name="Mitarbeiter" value="Mitarbeiter"/>
        </form>
    </body>
</html>