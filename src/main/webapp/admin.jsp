<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html lang='de'>
<html>
<head>
    <title>AdminPanel</title>
</head>
<body onload="javascript:enableButtonsAdmin()">
    Verkaufte Tickets: <%=
        request.getAttribute("VerkaufteTickets")
    %> <br>
    Durchschnittliche Einnahmen: <%=
        request.getAttribute("DurchschnittlicheEinnahmen")
    %> <br>
    Gesamteinnahmen: <%=
        request.getAttribute("Gesamteinnahmen")
    %> <br> <br>
    Anzahl Einfahrten: <%=
        request.getAttribute("AnzahlEinfahrten")
    %> <br>
    Anzahl Ausfahrten: <%=
        request.getAttribute("AnzahlAusfahrten")
    %> <br>
    Auslastung: <%=
        request.getAttribute("Auslastung")
    %> % <br> <br>

    Preis fuer ein bestimmtes Ticket setzen <br>
    <form method="POST">
        <input type="hidden" name="aktion" value="admin">
        <input type="number" name="preis">
        <select name="ticket" id="selectTicket">
            <c:forEach items="${bezahlteTickets}" var="bTickets">
                <option value="${bTickets.ID}"> Ticket: ${bTickets.ID} Preis: ${bTickets.gesamtpreis}</option>
            </c:forEach>

            <c:forEach items="${aktiveTickets}" var="aTickets">
                <option value="${aTickets.ID}"> Ticket: ${aTickets.ID} Preis: ${aTickets.gesamtpreis}</option>
            </c:forEach>
        </select>
        <button id="outBtn">Submit</button>
        <%
            // der button sollte disabled sein geht so aber noch nicht wegen javascript oder so
            // todo fabian schau mal bitte js ist ja dein ding
        %>
    </form> <br> <br>

    Preis global für alle in Zukunft erstellte Tickets setzen <br>
    <form method="POST">
        <input type="number" name="preisGlobal">
        <button id="gBtn">Submit</button>
    </form>

    Öffnungs- und Schließzeiten festlegen <br>
    <form method="POST">
        <input type="time" id="oeffnungszeit" name="oeffnungszeit">
        <input type="time" id="schliesszeit" name="schliesszeit">
    </form>

    Monatsticket erstellen <br>
    <form method="POST">
        <input type="hidden" name="aktion" value="checkInMonat" >
        <button id="checkInMonBtn">Erstellen</button> <br>
    </form>
</body>
</html>
