<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Online-Banking</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <p>Geben Sie Ihre Bank ein</p>
        <br>
        <form action="${pageContext.request.contextPath}/MainServlet" method="post">
            <input type="text" name="BankID" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="WahlBank" value="Einloggen"/>
        </form>
    </body>
</html>