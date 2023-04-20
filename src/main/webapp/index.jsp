<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>Parkhaus</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2 id="ueberschrift">Parkhaus</h2>
<div id="dashboard">
    <div id="Einfahrt">
        <h2>Einfahrt</h2>
        <img>
        <button>Check In</button>
    </div>
    <div id="Ausfahrt">
        <h2>Ausfahrt</h2>
        <img>
        <form methode="GET" action="/demo_war_exploded/PFAD">
            <input type="text" id="ticketIdAusfahrt" name="ticketIdAusfahrt" placeholder="TicketID">
            <button id="outBtn" name="ausfahrtBtn">Check Out</button>

        </form>
    </div>
    <div id="automat">
        <h2>Bezahlautomat</h2>
        <form methode="GET" action="/demo_war_exploded/PFAD">
            <input type="text" id="ticketIdBezahlen" name="ticketIdBezahlen" placeholder="TicketID">
            <input type="text" id="einzahlung" name="einzahlung" placeholder="Geld eingeben">
            <button id="bezahlBtn" name="bezahlBtn">Check Out</button>
        </form>
    </div>
</div>
</body>