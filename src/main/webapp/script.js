function main(freiePlaetze, auslastung) {
    document.getElementById("checkInBtn").addEventListener("click", disableCheckIn(freiePlaetze));
    progress(auslastung);
    enableButtons();
}

function progress(number) {
    var element = document.getElementById("progressBar"); // wählt element aus
    element.style.width = number + "%"; // stellt breite ein
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
    if (document.getElementById('selectStartCharge').options.length > 0) { // falls nicht ladende Tickets existieren, die noch nicht bezahlt wurden
        const button2 = document.getElementById("startChargeBtn");
        button2.disabled = false; // dann werden die Buttons zum Laden aktiviert
    }
    if (document.getElementById('selectStopCharge').options.length > 0) { // falls ladende Tickets existieren
        const button3 = document.getElementById("stopChargeBtn");
        button3.disabled = false; // dann werden die Buttons zum Stoppen aktiviert
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
