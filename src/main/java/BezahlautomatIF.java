public interface BezahlautomatIF {
    void bezahlen(TicketIF ticket);

    /**
     * entwertet Ticket, indem Auto ausfährt und die Schranke sich öffnet
     * @return true für entwertetes Ticket, false für Komplikationen bei der Ausfahrt
     */
}
