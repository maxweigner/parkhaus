package services;

public interface BezahlautomatIF {

    /**
     * entwertet services.Ticket, indem Auto ausfährt und die services.Schranke sich öffnet
     * @return true für entwertetes services.Ticket, false für Komplikationen bei der Ausfahrt
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

    /**
     * Gibt das für die Einnahmenanalyse zuständige Objekt zurück
     * @return Einnahmen Object
     */
    EinnahmenIF getEinnahmen();


}
