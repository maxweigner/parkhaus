package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.LocalDateTime;

public interface TicketIF {
    /**
     * eindeutiger Schlüssel
     * @return : ID als int
     */
    int getID();

    /**
     * Preis pro Stunde
     * @return : Stundenpreis als int
     */
    int getPreis();

    /**
     * Setzt den Preis pro Stunde für das Ticket
     */
    void setPreis(int preis);

    /**
     * Gibt Einfahrtszeitpunkt
     * @return : Zeitstempel von TimeIF
     */
    LocalDateTime getEinfahrtsZeit();

    /**
     * Gibt Zahlungszeitpunkt
     * @return : Zeitstempel von TimeIF
     */
    LocalDateTime getZahlungsZeit();

    /**
     * Gibt Ausfahrtszeitpunkt
     * @return : Zeitstempel von TimeIF
     */
    LocalDateTime getAusfahrtsZeit();

    /**
     * Setzt Zeitpunkt der Einfahrt
     * @param time: Zeitstempel von TimeIF
     */
    void setEinfahrtsZeit(LocalDateTime time);

    /**
     * Setzt Zeitpunkt der Zahlung
     * @param time: Zeitstempel von TimeIF
     */
    void setZahlungsZeit(LocalDateTime time);
    /**
     * Setzt Zeitpunkt der Ausfahrt
     * @param time: Zeitstempel von TimeIF
     */
    void setAusfahrtsZeit(LocalDateTime time);

    /**
     * Setzt den Status des tickets auf bezahlt
     */
    void setBezahlt();

    boolean istBezahlt();

    boolean istGueltig();
    void setGueltigkeit(boolean boo);

    void setGesamtpreis(int gesamtpreis);

    int getGesamtpreis();

    void setStartLadeZeit(LocalDateTime start);

    /**
     * ...and sets start time to null
     * @return
     */
    LocalDateTime getStartLadeZeit();
    boolean isMonatsTicket();
    void setBezahlung(boolean b);
    void setMonatsTicket(boolean monatsTicket);

    void ausfahren(SchrankeIF schranke, LocalDateTime time);
    void bezahlen(LocalDateTime bezahlZeit, EinnahmenIF einnahmen);
    void startAufladen(LocalDateTime time);
    void endAufladen(LocalDateTime time, int stundenpreis);
}
