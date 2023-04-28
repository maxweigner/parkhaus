function enableButtons() {
    if (document.getElementById('selectAusfahrt').options.length > 0) { // falls Liste nicht leer
        const button = document.getElementById("outBtn");
        button.disabled = false; // dann werden die Buttons aktiviert
    }
    if (document.getElementById('selectBezahlen').options.length > 0) {
        const button = document.getElementById("bezahlBtn");
        button.disabled = false;
    }
}
