function main(auslastung) {
    document.getElementById("checkInBtn").addEventListener("click", disableCheckIn(auslastung));
    enableButtons();
}

function enableButtons() {
    if (document.getElementById('selectAusfahrt').options.length > 0) { // falls bezahlte Tickets existieren, die sich noch im Parkhaus befinden
        const button = document.getElementById("outBtn");
        button.disabled = false; // dann werden die Buttons zur Ausfahrt aktiviert
    }
    if (document.getElementById('selectBezahlen').options.length > 0) { // falls gültige Tickets existieren, die noch nicht bezahlt wurden
        const button1 = document.getElementById("bezahlBtn");
        button1.disabled = false; // dann werden die Buttons zum Bezahlen aktiviert
    }
}

function enableButtonsAdmin() {
    if (document.getElementById('selectTicket').options.length > 0) { // falls Liste nicht leer
        const button = document.getElementById("outBtn");
        button.disabled = false; // dann werden die Buttons aktiviert
    }

}

function disableCheckIn(number){
    const button = document.getElementById("checkInBtn");
    if (number == 0){ // falls keine Tickets zur Einfahrt mehr übrig sind
        button.disabled = true; // werden keine neuen ausgegeben
    } else {
        button.disabled = false;
    }
}
