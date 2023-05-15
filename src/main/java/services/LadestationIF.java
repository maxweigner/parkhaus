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

    boolean getBelegt();

}
