<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html lang='de'>
<head>
    <title>Parkhaus</title>
    <script src="script.js"></script>
    <link rel="stylesheet" href="style.css">
    <link rel="icon" type="image/png" href="favicon.png">
    <meta http-equiv="refresh" content="180; url=">
</head>
<body onload="javascript:main(${freiePlaetze})">
<h2 id="ueberschrift">Parkhaus</h2>
<div id="dashboard">
    <div id="Einfahrt">
        <h2>Einfahrt</h2>
        <img>
        <form method="POST">
            <input type="hidden" name="aktion" value="checkIn">
            <input type="datetime-local" step="1" name="checkInTime" value="2023-04-20T09:00:00">
            <button id="checkInBtn">Check In</button> <br>
        </form>
        <div id="Auslastung">
            <h3>Auslastung</h3>
            <h4>${auslastung}%</h4>
        </div>
        <div id="Ladestation">
            <h2>Ladestation</h2>
            <form method="post">
                <input type="hidden" name="aktion" value="startLaden">
                <input type="datetime-local" step="1" name="startChargeTime" value="2023-04-20T09:00:00">
                <select name="ticket" id="selectStartCharge">
                    <c:forEach items="${nichtLadendeTickets}" var="dTickets">
                        <option value="${dTickets.ID}"> Ticket-Nr: ${dTickets.ID}</option>
                    </c:forEach>
                </select>
                <button id="startChargeBtn" name="startChargeBtn" disabled>Start</button>
            </form>
            <form method="post">
                <input type="hidden" name="aktion" value="stopLaden">
                <input type="datetime-local" step="1" name="stopChargeTime" value="2023-04-20T09:00:00">
                <select name="ticket" id="selectStopCharge">
                    <c:forEach items="${ladendeTickets}" var="cTickets">
                        <option value="${cTickets.ID}"> Ticket-Nr: ${cTickets.ID}</option>
                    </c:forEach>
                </select>
                <button id="stopChargeBtn" name="stopChargeBtn" disabled>Stop</button>
            </form>
        </div>
    </div>
    <div id="Ausfahrt">
        <h2>Ausfahrt</h2>
        <img>
        <form method="POST">
            <input type="hidden" name="aktion" value="checkOut">
            <input type="datetime-local" step="1" name="checkOutTime" value="2023-04-20T09:00:00">
            <select name="ticket" id="selectAusfahrt">
                <c:forEach items="${bezahlteTickets}" var="bTickets">
                    <option value="${bTickets.ID}"> Ticket ${bTickets.ID} Bezahlt: ${bTickets.gesamtpreis}€</option>
                </c:forEach>
            </select>
            <button id="outBtn" disabled>Check Out</button>
        </form>
    </div>
    <div id="automat">
        <h2>Bezahlautomat</h2>
        <form method="post">
            <input type="hidden" name="aktion" value="bezahlen">
            <input type="datetime-local" step="1" name="bezahlenTime" value="2023-04-20T09:00:00">
            <select name="ticket" id="selectBezahlen">
                <c:forEach items="${aktiveTickets}" var="aTickets">
                    <option value="${aTickets.ID}"> Ticket-Nr: ${aTickets.ID} Preis: ${aTickets.gesamtpreis}</option>
                </c:forEach>
            </select>
            <button id="bezahlBtn" name="bezahlBtn" disabled>Bezahlen</button>
        </form>
    </div>
</div>
</body>
