public interface BezahlautomatIF {

    /**
     * entwertet Ticket, indem Auto ausfährt und die Schranke sich öffnet
     * @return true für entwertetes Ticket, false für Komplikationen bei der Ausfahrt
     */
    boolean bezahlen(TicketIF ticket);

    /**
     * Zahlt in den Automaten ein
     * @param summe Die eingezahlte Summe
     */
    void einzahlen(long summe);

    /**
     * Gibt das aktuell im Automaten vorhandene Guthaben aus
     * @return Das aktuelle Guthaben
     */
    long getGuthaben();

}
