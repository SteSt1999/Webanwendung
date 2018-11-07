<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Kunden erstellt</title>
    </head>
    <body>
        <h1>Mitarbeiter</h1>
        <br>Es wurde ein Kunde erstellt.
        <!-- TODO Kunden ID einfügen -->
        <br>Sein Benutzername ist:
        <%
            out.println("ausfüllen");
        %>
        <br>Das zugehörige Konto wurde mit einem Guthaben von 0 erstellt.
        <br><br>
        <form action="${pageContext.request.contextPath}/MAServlet" method="post">
            <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>