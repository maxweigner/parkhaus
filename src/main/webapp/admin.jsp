<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html lang='de'>
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
