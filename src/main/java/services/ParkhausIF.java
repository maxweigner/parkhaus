package services;

import models.Ticket;
import models.TicketIF;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
     * Erstellt ein neues Ticket zum angegebenen Zeitpunkt
     * @return Ticket
     */

    Ticket einfahrt(SchrankeIF schranke, int preis, LocalDateTime aktuelleZeit);

    /**
     * Bearbeitet Einfahrten mit Monatstickets
     * @param monatsticket
     */
    void einfahrt(SchrankeIF schranke, TicketIF monatsticket);

    /**
     * Ticket prüfen und Schranke öffnen, wenn in den letzten 15 Minuten bezahlt wurde
     * @return true für Erfolg, sonst false
     */
    boolean ausfahrt(TicketIF ticket, SchrankeIF schranke);

    /**
     * Erstellt Monatsticket
     * @param preis: Preis für die monatliche Nutzung
     * @param time: Start der Frist
     * @return Monatsticket
     */
    Ticket erstelleMonatsticket(int preis, LocalDateTime time);

    /**
     * Startet den Ladevorgang an einer Ladesäule
     * @param ticket Ticket, dass zur Abrechnung genutzt wird
     */
    void startLaden(TicketIF ticket);

    /**
     * Stoppt den Ladevorgang an einer Ladesäule
     * @param ticket: Ticket, dass zur Abrechnung genutzt wird
     */
    void stopLaden(TicketIF ticket, int stundenPreis);


    /**
     * Gibt die Anzahl an noch verfügbaren Plätzen aus
     * @return Anzahl noch freier Plätze
     */
    int getAnzahlFreiePlaetze();

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
     * Sucht aus allen Tickets die an einer Ladestation heraus
     * @return Liste mit Tickets, dessen zugehöriges Fahrzeug geladen wird
     */
    TicketIF[] getLadendeTickets();

    /**
     * Sucht aus allen Tickets die sich im Parkhaus befinden und nicht laden
     * @return Liste mit Tickets
     */
    TicketIF[] getNichtLadendeTickets();

    /**
     * Sucht alle Monatstickets, die sich nicht im Parkhaus befinden
     * @return Liste mit Monatstickets
     */
    TicketIF[] getMonatstickets();

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

    /**
     * Gibt aktuelle Zeit aus
     * @return LocalDateTime der Parkhauszeit
     */
    LocalDateTime getAktuelleZeit();

    /**
     * Setzt die Anzahl an verfügbaren Plätzen
     * @param spaces Die neue Anzahl der freien Plätze
     */
    void setAnzahlPlaetze(int spaces);

    /**
     * Gibt die aktuell für das Parkhaus gültige Zeit aus.
     * Die neue Zeit muss chronologisch der alten Zeit folgen.
     * @param now die neue Zeit
     * @return true wenn die neue Zeit übernommen wurde
     */
    boolean setAktuelleZeit(LocalDateTime now);

}
