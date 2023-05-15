package services;

import models.TicketIF;

public interface LadestationIF {

    /**
     * Startet den Ladevorgang an einer Ladesäule
     * @param ticket: Ticket, dass zur Abrechnung genutzt wird
     * @return: false für Fehler, true für Erfolg
     */
    boolean startLaden(TicketIF ticket);

    /**
     * Stoppt den Ladevorgang an einer Ladesäule
     * @param ticket: Ticket, dass zur Abrechnung genutzt wird
     * @return: false für Fehler, true für Erfolg
     */
    boolean stopLaden(TicketIF ticket);

    /**
     * Gibt die Abrechnungsnummer zurück, die der Ticket-ID entspricht
     * @return: Abrechnungsnummer
     */
    int getAbrechnungsnummer();

    /**
     * Gibt die Seriennummer der Ladestation zurück
     * @return: Seriennummer
     */
    int getSeriennummer();

    /**
     * Gibt an, ob die Ladestation belegt ist oder nicht
     * @return: true für belegt, false für frei
     */
    boolean getBelegt();

    /**
     * Gibt das Ticket zurück, auf welchem der aktuelle Ladevorgang abgerechnet wird
     * @return: null für kein Ladevorgang. Ansonsten das Ticket, dass dem Ladevorgang zugeordnet wird
     */
    TicketIF getTicket();

}
