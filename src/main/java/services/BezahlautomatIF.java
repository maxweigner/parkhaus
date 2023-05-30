package services;

import models.TicketIF;

import java.time.LocalDateTime;

public interface BezahlautomatIF {

    /**
     * bezahlt ein ticket wenn genug guthaben vorhanden ist
     * @param ticket das ticket welches bezahlt werden soll
     * @return true für entwertetes ticket
     */
    boolean bezahlen(TicketIF ticket);

    /**
     * bezahlt ein ticket ohne das guthaben zu beachten
     * @param ticket das ticket welches bezahlt werden soll
     * @param now die Endparkzeit
     */
    void bezahlen(TicketIF ticket, LocalDateTime now);

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
