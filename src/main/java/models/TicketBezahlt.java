package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class TicketBezahlt  implements TicketState {
    @Override
    public void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time) {
        if (!ticket.istBezahlt() || !ticket.istGueltig() ||
                !ticket.getZahlungsZeit().isAfter(ticket.getAusfahrtsZeit().minus(Duration.ofMinutes(15)))) {
            ticket.setBezahlung(false);
            return;
        }

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

    }

    @Override
    public void startAufladen(TicketIF ticket, LocalDateTime time) {

    }

    @Override
    public void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Bezahlt");
    }
}
