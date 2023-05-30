package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.LocalDateTime;

public class TicketAusgefahren implements TicketState {
    @Override
    public void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ausgefahren");
    }

    @Override
    public void bezahlen(TicketIF ticket, LocalDateTime bezahlZeit, EinnahmenIF einnahmen) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ausgefahren");
    }

    @Override
    public void startAufladen(TicketIF ticket, LocalDateTime time) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ausgefahren");
    }

    @Override
    public void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ausgefahren");
    }
}
