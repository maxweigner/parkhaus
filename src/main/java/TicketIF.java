import java.time.LocalDateTime;

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
     * Einfahrtszeitpunkt / Bezahlzeitpunkt im Parkhaus
     * abhängig davon ob schon gezahlt wurde
     * @return: Zeitstempel von TimeIF
     */
    LocalDateTime getZeit();

    /**
     * Setzt Zeitpunkt der Zahlung
     * @param time: Zeitstempel von TimeIF
     */
    void setZeit(LocalDateTime time);

    /**
     * Setzt den Status des tickets auf bezahlt
     */
    void setBezahlt();

    boolean istBezahlt();
}
