package services;

import java.util.LinkedList;
import java.util.List;

public interface ParkhausIF {

    /**
     * Erstellt ein neues services.Ticket zum Zeitpunkt der Einfahrt
     * @return services.Ticket
     */
    TicketIF einfahrt(SchrankeIF schranke);

    /**
     * Zahlungsvorgang, um mit einem services.Ticket die services.Schranke zu öffnen
     * @param ticket: gültiges services.Ticket
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
     * @return: Liste mit allen Tickets
     */
    TicketIF getTicket(int id);

    /**
     * Sucht aus allen Tickets die bezahlten heraus
     * @return: Liste mit bezahlten Tickets
     */
    TicketIF[] getBezahlteTickets();

    /**
     * Sucht aus allen Tickets die unbezahlten heraus
     * @return: Liste mit unbezahlten Tickets
     */
    TicketIF[] getUnbezahlteTickets();

    /**
     * Gibt den Bezahlautomaten des Parkhauses zurück
     * @return Bezahlautomat Object
     */
    BezahlautomatIF getAutomat();

}
