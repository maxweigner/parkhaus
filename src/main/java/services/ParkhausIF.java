package services;

import models.Ticket;
import models.TicketIF;

import java.time.LocalDateTime;

public interface ParkhausIF {

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
    TicketIF einfahrt(SchrankeIF schranke);

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
    Ticket einfahrt(SchrankeIF schranke, int preis);

    /**
     * Ticket prüfen und Schranke öffnen, wenn in den letzten 15 Minuten bezahlt wurde
     * @param ticket: gültiges Ticket
     */
    boolean ausfahrt(TicketIF ticket, SchrankeIF schranke);

    /**
     * Gibt die Anzahl an noch verfügbaren Plätzen aus
     * @return Anzahl noch freier Plätze
     */
    int getAnzahlFreiePlaetze();

    /**
     * Setzt die Anzahl an verfügbaren Plätzen
     * @param spaces Die neue Anzahl der freien Plätze
     */
    void setAnzahlPlaetze(int spaces);

    /**
     * Gibt alle Einfahrtsschranken aus
     * @return Array mit allen Einfahrtsschranken
     */
    SchrankeIF[] getEinfahrtSchranken();

    /**
     * Gibt alle Ausfahrtsschranken aus
     * @return Array mit allen Ausfahrtsschranken
     */
    SchrankeIF[] getAusfahrtSchranken();

    /**
     * Sucht Ticket anhand der ID
     * @param id: Nummer des geforderten Tickets
     * @return Liste mit allen Tickets
     */
    TicketIF getTicket(int id);

    /**
     * Sucht aus allen Tickets die bezahlten heraus, die sich aber noch im Parkhaus befinden
     * @return Liste mit bezahlten und gültigen Tickets
     */
    TicketIF[] getBezahlteTickets();

    /**
     * Sucht aus allen Tickets die unbezahlten heraus
     * @return Liste mit unbezahlten Tickets
     */
    TicketIF[] getUnbezahlteTickets();

    /**
     * Gibt den Bezahlautomaten des Parkhauses zurück
     * @return Bezahlautomat Object
     */
    BezahlautomatIF getAutomat();

    /**
     * Gibt die Kapazität des Parkhauses zurück
     * @return int Anzahl Gesamtplätze
     */
    int getKapazitaet();
}
