package models;

import java.time.LocalDateTime;

import services.EinnahmenIF;
import services.SchrankeIF;

public interface TicketState {
    void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time);
    void bezahlen(TicketIF ticket, LocalDateTime bezahlZeit, EinnahmenIF einnahmen);
    void startAufladen(TicketIF ticket, LocalDateTime time);
    void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis);
}
