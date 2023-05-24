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
        <form method="POST">
            <input type="hidden" name="aktion" value="checkIn">
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
                <select name="ticket" id="selectStartCharge">
                    <c:forEach items="${nichtLadendeTickets}" var="dTickets">
                        <option value="${dTickets.ID}"> Ticket-Nr: ${dTickets.ID}</option>
                    </c:forEach>
                </select>
                <button id="startChargeBtn" name="startChargeBtn" disabled>Start</button>
            </form>
            <form method="post">
                <input type="hidden" name="aktion" value="stopLaden">
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
            <select name="ticket" id="selectBezahlen">
                <c:forEach items="${aktiveTickets}" var="aTickets">
                    <c:if test="${aTickets.monatsTicket == true}">
                        <option value="${aTickets.ID}"> Ticket-Nr: ${aTickets.ID} (Monatsticket)</option>}
                    </c:if>
                    <c:if test="${aTickets.monatsTicket == false}">
                        <option value="${aTickets.ID}"> Ticket-Nr: ${aTickets.ID}</option>}
                    </c:if>
                </c:forEach>
            </select>
            <button id="bezahlBtn" name="bezahlBtn" disabled>Bezahlen</button>
        </form>
    </div>
</div>
<br><br>
<form method="POST">
    <input type="hidden" name="aktion" value="changeTime">
    <input type="datetime-local" step="1" name="changeTimeTo" value="<c:out value ="${currentTime}"/>">
    <button id="changeTimeBtn">Change Time</button> <br>
</form>
</body>