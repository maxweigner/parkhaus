<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>Parkhaus</title>
    <link rel="stylesheet" href="style.css">
    <meta http-equiv="refresh" content="180; url=">
</head>
<body>
<h2 id="ueberschrift">Parkhaus</h2>
<div id="dashboard">
    <div id="Einfahrt">
        <h2>Einfahrt</h2>
        <img>
        <form method="POST" action="/Team_4_sose23_war/checkIn">
            <label for="schranke">Schranke auswählen:</label>
            <select name="schranke" id="schranke">
                <%
                    // anzahl der schranken abfragen
                    int anzahlSchranken = (Integer) request.getAttribute("anzahl-schranken-einfahrt");

                    // die schranken als auswahlmöglichkeiten im drop-down ausgeben
                    for (int i = 0; i < anzahlSchranken; i++) {
                        out.println("<option value=\"" + i + "\">Schranke " + (i+1) + "</option>");
                    }
                %>
            </select> <br>
            <input type="hidden" name="checkIn" value="true">
            <button>Check In</button> <br>
        </form>
        <%
            Object ticketID = request.getAttribute("ticketID");
            if (ticketID != null) {
                out.println("Ihr Ticket hat die Nummer: " + ticketID);
            }
        %>
    </div>
    <div id="Ausfahrt">
        <h2>Ausfahrt</h2>
        <img>
        <form method="POST" action="/Team_4_sose23_war/checkOut">
            <input type="text" id="ticketIdAusfahrt" name="id" placeholder="TicketID">
            <button id="outBtn">Check Out</button>

        </form>
    </div>
    <div id="automat">
        <h2>Bezahlautomat</h2>
        <form method="POST" action="/Team_4_sose23_war/">
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
