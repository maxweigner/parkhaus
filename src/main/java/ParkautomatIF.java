public interface ParkautomatIF {

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
    TicketIF erstellen();
    /**
     * Zahlungsvorgang, um mit einem Ticket die Schranke zu öffnen
     * @param ticket: gültiges Ticket
     */
    void bezahlen(TicketIF ticket);

    /**
     * entwertet Ticket, indem Auto ausfährt und die Schranke sich öffnet
     * @return true für entwertetes Ticket, false für Komplikationen bei der Ausfahrt
     */
    boolean entwerten(TicketIF ticket);

    /**
     * Reduziert die Anzahl freier Plätze um genau 1
     */
    void einfahrt();

    /**
     * Gibt die Anzahl an noch verfügbaren Plätzen aus
     * @return Anzahl noch freier Plätze
     */
    int freeSpaces();

    /**
     * Setzt die Anzahl an verfügbaren Plätzen
     * @param spaces Die neue Anzahl der freien Plätze
     */
    void setSpacesAmount(int spaces);
}
