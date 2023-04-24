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
        <form methode="GET" action="/Team_4_sose23_war/checkIn">
            <input type="hidden" name="checkIn" value="true">
            <button>Check In</button>

        </form>
    </div>
    <div id="Ausfahrt">
        <h2>Ausfahrt</h2>
        <img>
        <form methode="GET" action="/Team_4_sose23_war/checkOut">
            <input type="text" id="ticketIdAusfahrt" name="id" placeholder="TicketID">
            <button id="outBtn">Check Out</button>

        </form>
    </div>
    <div id="automat">
        <h2>Bezahlautomat</h2>
        <form methode="GET" action="/Team_4_sose23_war/">
            <input type="text" id="ticketIdBezahlen" name="ticketIdBezahlen" placeholder="TicketID">
            <input type="text" id="einzahlung" name="einzahlung" placeholder="Geld eingeben">
            <button id="bezahlBtn" name="bezahlBtn">Check Out</button>
        </form>
    </div>
</div>
<script type="text/javascript">
    var msg = "";
    if (0 < ${param.id}){
        msg = "Please keep your id: ${param.id}";
    } else if (0 == ${param.id}){
        msg="Thanks for your visit!";
    } else if (-1 == ${param.id}){
        msg="Sorry, invalid input";
    }
    alert(msg);
</script>
</body>
