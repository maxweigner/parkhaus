public interface ParkautomatIF {

    /**
     * erstellt ein neues Ticket für einfahrendes Fahrzeug
     * @return: erstelltes Ticket
     */
    TicketIF erstellen();
    /**
     * verarbeitet Bezahlvorgang
     * @param ticket: gültiges Ticket
     */
    void bezahlen(TicketIF ticket);

    /**
     * entwertet Ticket, indem Auto ausfährt und die Schranke sich öffnet
     * @return true für entwertetes Ticket, false für Komplikationen bei der Ausfahrt
     */
    boolean entwerten(TicketIF ticket);
}
