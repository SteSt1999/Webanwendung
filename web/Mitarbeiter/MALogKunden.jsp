<%@ page import="Servlet.MitarbeiterServlet" %>
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
            out.println(MitarbeiterServlet.getKundenLogs());
        %>
        <br><br>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>