package models;

import services.EinnahmenIF;
import services.SchrankeIF;

import java.time.Duration;
import java.time.LocalDateTime;

public class TicketEingefahren  implements TicketState {
    @Override
    public void ausfahren(TicketIF ticket, SchrankeIF schranke, LocalDateTime time) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Eingefahren");
    }

    @Override
    public void bezahlen(TicketIF ticket, LocalDateTime bezahlZeit, EinnahmenIF einnahmen) {
        ticket.setZahlungsZeit(bezahlZeit);
        ticket.setBezahlt();

        if (ticket.isMonatsTicket() && ticket.getGesamtpreis() != 0) {
            einnahmen.addIncome(50);
            return;
        }

        // berechnet kosten mit parkdauer in stunden * preis des tickets
        int preis = (int) Duration.between(ticket.getEinfahrtsZeit(), ticket.getZahlungsZeit()).toHours() * ticket.getPreis();
        int gesamtPreis = ticket.getGesamtpreis() + preis;

        ticket.setGesamtpreis(gesamtPreis);
        einnahmen.addIncome(gesamtPreis);
    }

    @Override
    public void startAufladen(TicketIF ticket, LocalDateTime time) {
        ticket.setStartLadeZeit(time);
        ticket.setLadend(true);
    }

    @Override
    public void endAufladen(TicketIF ticket, LocalDateTime time, int stundenpreis) {
        throw new IllegalStateException("Das Ticket befindet sich im Zustand Eingefahren");
    }
}
