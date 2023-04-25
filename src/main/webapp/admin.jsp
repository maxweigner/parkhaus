<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 25.04.2023
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPanel</title>
</head>
<body>
Verkaufte Tickets: <%=
    request.getAttribute("VerkaufteTickets")
%> <br>
Durchschnittliche Einnahmen: <%=
    request.getAttribute("DurchschnittlicheEinnahmen")
%> <br>
Gesamteinnahmen: <%=
    request.getAttribute("Gesamteinnahmen")
%>
</body>
</html>
