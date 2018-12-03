<%@ page import="Logik.Sessionsteuerung.Log" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Bank-Transaktionen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Hier sehen Sie die gesamten Transaktionen der Bank.
        <br><br>
        <%
            out.println(Log.ausgabeBankLog());
        %>
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>