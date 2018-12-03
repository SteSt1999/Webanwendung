<%@ page import="Logik.Umwandlung" %>
<%@ page import="Logik.Verwaltung.Kunde" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Online-Banking</title>
    </head>
    <body>
        <h1>Online-Banking</h1>
        <br>Sie sind eingeloggt.
        <br>
        <br>Kontostand:
        <%
            Kunde kunde = new Kunde((String) session.getAttribute("kunde"), (String) session.getAttribute("bank"));
            out.println(Umwandlung.centToEuroString(kunde.getKontostand()));
        %>
        <br>
        <form action="${pageContext.request.contextPath}/OBServlet" method="post">
            <br><br>
            <input type="submit" name="Ueberweisung" value="Überweisen"/>
            <br><br>
            <input type="submit" name="Transactions" value="Kontoverlauf"/>
            <br><br>
            <input type="submit" name="Abmelden" value="Abmelden"/>
        </form>
    </body>
</html>