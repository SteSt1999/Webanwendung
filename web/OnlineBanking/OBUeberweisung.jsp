<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Überweisung</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Hier können Sie eine Überweisung tätigen.
        <br>
        <form action="${pageContext.request.contextPath}/OnlineBankingServlet" method="post">
            <p>Empfänger: </p>
            <input type="text" name="Empfaenger" size=20 maxlength=50>
            <p>Summe: </p>
            <input type="number" min="0" step="0.01" data-number-to-be-fixed="2" data-number-stepfactor="100"
                   name="Summe" size=20 maxlength=50>
            €<br><br>
            <input type="submit" name="Ueberweisen" value="Überweisen"/>
            <br>
            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>