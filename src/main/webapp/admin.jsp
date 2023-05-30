<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html lang='de'>
<html>
<head>
    <title>AdminPanel</title>
    <script src="script.js"></script>
    <link rel="stylesheet" href="style.css">
    <link rel="icon" type="image/png" href="favicon.png">
</head>
<body onload="javascript:enableButtonsAdmin()">
<div class="header">
    <h1 style="text-align:center">Verwaltung</h1>
</div>
    <div id="uebersicht">
        <div id="adminAllgemein">
            <h2>📊 Statistik 📊 </h2>
            <h3>Verkaufte Tickets: <%=request.getAttribute("VerkaufteTickets")%></h3>
            <h3>Durchschnittliche Einnahmen: <%=request.getAttribute("DurchschnittlicheEinnahmen")%></h3>
            <h3>Gesamteinnahmen: <%=request.getAttribute("Gesamteinnahmen")%></h3>
            <h3>Anzahl Einfahrten: <%=request.getAttribute("AnzahlEinfahrten")%></h3>
            <h3>Anzahl Ausfahrten: <%=request.getAttribute("AnzahlAusfahrten")%></h3>
            <h3>Auslastung: <%=request.getAttribute("Auslastung")%> %</h3>
            <h3>Öffnungs- und Schließzeiten festlegen </h3>
            <form method="POST">
                <input type="time" id="oeffnungszeit" name="oeffnungszeit" value="<c:out value="${oeffnungszeit}" />">
                <input type="time" id="schliesszeit" name="schliesszeit" value="<c:out value="${schliesszeit}" />">
            </form>
        </div>
        <div id="adminPreise">
            <h2>💵 Preise 💵</h2>
            <h3>Preis fuer ein bestimmtes Ticket setzen </h3>
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
                <button id="outBtn" class="button">Submit</button>
            </form>
            <h3>Preis global für alle in Zukunft erstellte Tickets setzen </h3>
            <form method="POST">
                <input type="hidden" name="aktion" value="globalPreis">
                <input type="number" name="preisGlobal">
                <button id="gBtn" class="button">Submit</button>
            </form>
        </div>
        <div id="rest">
            <h2>Sonstiges</h2>
            <h3>Monatsticket erstellen </h3>
            <form method="POST">
                <input type="hidden" name="aktion" value="checkInMonat" >
                <button id="checkInMonBtn" class="button">Erstellen</button> <br>
            </form>
            <h3>Parkplatz</h3>
            <a href="${pageContext.request.contextPath}" class="button">Parkhaus</a>
        </div>
    </div>
</body>
</html>
