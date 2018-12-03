<%@ page import="Logik.Sessionsteuerung.Log" %>
<%@ page import="Logik.Verwaltung.ATM" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>ATM-Transaktionen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Hier sehen Sie die Transaktionen des ATMs.
        <br><br>
        <%
            ATM atm = new ATM((String) session.getAttribute("atmAuswahl"));
            out.println(Log.ausgabeATMLog(atm));
        %>
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>