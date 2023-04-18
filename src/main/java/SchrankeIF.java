public interface SchrankeIF {
    /**
     * Öffnet die Schranke
     * @return true wenn die Schranke erfolgreich geöffnet wurde
     */
    boolean open();

    /**
     * Schließt eine gegebene Schranke
     * @return true wenn die Schranke erfolgreich geschlossen wurde
     */
    boolean close();

    /**
     * Weist der Schranke die Funktion Einfahrt oder Ausfahrt zu
     * @param einfahrtAusfahrt "einfahrt" oder "ausfahrt", definiert wofür die Schranke dient
     */
    void setSchranke(String einfahrtAusfahrt) throws IllegalArgumentException;

    /**
     * Gibt zurück ob die Schranke für Einfahrt oder Ausfahrt genutzt werden kann
     * @return
     */
    String getSchranke();

    /**
     * Prüft ob ein Ticket bezahlt wurde und öffnet und schließt darauf die Schranke. Ein Ticket gilt als bezahlt,
     * wenn innerhalb von 15 Minuten nach Bezahlzeitpunkt ausgefahren wird
     * @param ticket Das zu prüfende Ticket
     * @param schranke Die zu öffnende Schranke
     * @return true wenn das Ticket bezahlt wurde und false wenn die Schranke geschlossen bleibt
     */
    static boolean ausfahrt(TicketIF ticket, SchrankeIF schranke) {
        if(ticket == null) { return false; }

        if (ticket.getEnde() != null) {
            schranke.open();
            schranke.close();

            return true;
        }

        return false;
    }
}
