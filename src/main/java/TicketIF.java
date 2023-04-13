public interface TicketIF {
    /**
     * eindeutiger Schlüssel
     * @return: ID als int
     */
    int getID();

    /**
     * Preis pro Stunde
     * @return: Stundenpreis als int
     */
    int getPreis();

    /**
     * Einfahrtszeitpunkt ins Parkhaus
     * @return: Zeitstempel von TimeIF
     */
    TimeIF getStart();

    /**
     * Zahlungszeitpunkt am Automaten
     * @return: Zeitstempel von TimeIF
     */
    TimeIF getEnde();
}
