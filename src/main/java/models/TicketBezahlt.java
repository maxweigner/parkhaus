package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class TicketBezahlt  implements TicketState {
    @Override
    public void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time) {
        schranke.open();
        schranke.close();
        ticket.setGueltigkeit(false);

        if(ticket.isMonatsTicket()) {
            if(!ticket.getAusfahrtsZeit().isBefore(ticket.getEinfahrtsZeit().plusMonths(1)))
                return;

            ticket.setBezahlung(false);
            ticket.setGueltigkeit(true);
            ticket.setGesamtpreis(0);

        } else {
            ticket.setAusfahrtsZeit(LocalDateTime.parse(time.toString(), ISO_LOCAL_DATE_TIME));
        }
    }

    @Override
    public void bezahlen(TicketIF ticket, LocalDateTime bezahlZeit, EinnahmenIF einnahmen) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Bezahlt");
    }

    @Override
    public void startAufladen(TicketIF ticket, LocalDateTime time) {
        ticket.setStartLadeZeit(time);
    }

    @Override
    public void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Bezahlt");
    }
}
