<%@ page import="Servlet.MitarbeiterServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank-Transaktionen</title>
</head>
<body>
<h1>Bank-Transaktionen</h1>
<br>Hier sehen Sie die gesamten Transaktionen der Bank.
<br>
<br><br>
<%
    out.println(MitarbeiterServlet.getAllLogs());
%>
<br><br>
<form action="${pageContext.request.contextPath}/MitarbeiterServlet" method="post">
    <br><br>
    <input type="submit" name="Hauptmenu" value="Hauptmenü"/>
    <br><br>
    <input type="submit" name="Abmelden" value="Abmelden"/>
</form>
</body>
</html>