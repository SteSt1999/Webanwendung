<%@ page import="Servlet.MitarbeiterServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Kunden-Transaktionen</title>
    </head>
    <body>
        <h1>Kunden-Transaktionen</h1>
        <br>Hier sehen Sie die gesamten Transaktionen des Kunden.
        <br>
        <form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
            <br><br>
            <%
                out.println(MitarbeiterServlet.getUserLogs());
            %>
            <br><br>

            <br><br>
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>