package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TicketLadend  implements TicketState {
    @Override
    public void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ladend");
    }

    @Override
    public void bezahlen(TicketIF ticket, LocalDateTime bezahlZeit, EinnahmenIF einnahmen) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ladend");
    }

    @Override
    public void startAufladen(TicketIF ticket, LocalDateTime time) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Ladend");
    }

    @Override
    public void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis) {
        long ladeStunden = ticket.getStartLadeZeit().until(time, ChronoUnit.HOURS);
        ticket.setLadend(false);
        ticket.setGesamtpreis(ticket.getGesamtpreis() + stundenpreis * ((int) ladeStunden));
    }
}
