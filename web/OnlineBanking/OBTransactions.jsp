<%@ page import="Logik.Verwaltung.Kunde" %>
<%@ page import="Logik.Sessionsteuerung.Log" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kontoverlauf</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Hier sehen Sie alle Ihre Transaktionen:
        <br><br>
        <%
            Kunde kunde = (Kunde) session.getAttribute("kunde");
            out.println(Log.ausgabeKundenLog(kunde));
        %>
        <br><br>
        <form action="${pageContext.request.contextPath}/OBServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>