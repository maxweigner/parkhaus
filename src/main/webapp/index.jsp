<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html lang='de'>
<head>
    <title>Parkhaus</title>
    <link rel="stylesheet" href="style.css">
    <script src="script.js"></script>
    <meta http-equiv="refresh" content="180; url=">
</head>
<body onload="javascript:enableButtons()">
<h2 id="ueberschrift">Parkhaus</h2>
<div id="dashboard">
    <div id="Einfahrt">
        <h2>Einfahrt</h2>
        <img>
        <form method="POST">
            <input type="hidden" name="aktion" value="checkIn">
            <input type="datetime-local" step="1" name="checkInTime" value="2023-04-20T09:00:00">
            <button>Check In</button> <br>
        </form>
    </div>
    <div id="Ausfahrt">
        <h2>Ausfahrt</h2>
        <img>
        <form method="POST">
            <input type="hidden" name="aktion" value="checkOut">
            <input type="datetime-local" step="1" name="checkOutTime" value="2023-04-20T09:00:00">
            <select name="ticket" id="selectAusfahrt">
                <c:forEach items="${bezahlteTickets}" var="bTickets">
                    <option value="${bTickets.ID}"> Ticket ${bTickets.ID}</option>
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
