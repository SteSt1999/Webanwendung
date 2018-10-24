<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Überweisung</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Hier Können Sie eine Überweisung tätigen.
        <br>
        <form action="${pageContext.request.contextPath}/OnlineBankingServlet" method="post">
            <p>Empfänger: </p>
            <input type="text" name="Empfänger" size=20 maxlength=50>
            <p>Summe: </p>
            <input type="text" name="Summe" size=20 maxlength=50>
            <br><br>
            <input type="submit" name="Ueberweisen" value="Überweisen"/>
            <br>
        </form>
        <form action="${pageContext.request.contextPath}/OnlineBankingServlet" method="post">
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>