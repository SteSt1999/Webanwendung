<%@ page import="Logik.Verwaltung.Kunde" %>
<%@ page import="Logik.Sessionsteuerung.Log" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kunden-Transaktionen</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Hier sehen Sie die gesamten Transaktionen des Kunden.
        <br><br>
        <%
            out.println(Log.ausgabeKundenLog((Kunde) session.getAttribute("userAuswahl")));
        %>
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>