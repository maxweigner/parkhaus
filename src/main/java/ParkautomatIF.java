public interface ParkautomatIF {

    /**
     * erstellt ein neues Ticket für einfahrendes Fahrzeug
     * @return: erstelltes Ticket
     */
    TicketIF erstellen();
    /**
     * verarbeitet Bezahlvorgang
     * @param ticket: gültiges Ticket
     * @return: true für bezahlt, false für offener Betrag
     */
    boolean bezahlen(TicketIF ticket);

    /**
     * entwertet Ticket, indem Auto ausfährt und die Schranke sich öffnet
     * @return true für entwertetes Ticket, false für Komplikationen bei der Ausfahrt
     */
    boolean entwerten();

    /**
     * Gibt nächstgültige ID heraus
     * @return: ID als int
     */
    int getID();
}
