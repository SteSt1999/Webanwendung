<%@ page import="Datenbank.Datenbank" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Webanwendung</title>
  </head>
  <body>
  Test
  <%
    out.println("<br>Hallo");
    out.println(Datenbank.connect());
  %>
  </body>
</html>
