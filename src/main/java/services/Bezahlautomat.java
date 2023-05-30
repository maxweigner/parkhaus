package services;

import models.TicketIF;

import java.time.Duration;
import java.time.LocalDateTime;

public class Bezahlautomat implements BezahlautomatIF {

    private long guthaben = 0;
    private EinnahmenIF einnahmen = new Einnahmen();

    @Override
    public boolean bezahlen(TicketIF ticket) {
        LocalDateTime now = LocalDateTime.now();
        // parkdauer in stunden * preis des tickets
        int preis = (int) Duration.between(ticket.getEinfahrtsZeit(), now).toHours() * ticket.getPreis();

        if (guthaben >= preis) {
            ticket.setZahlungsZeit(LocalDateTime.now());
            guthaben -= preis;
            ticket.setBezahlt();

            einnahmen.addIncome(preis);

            return true;
        }

        return false;
    }

    @Override
    public void bezahlen(TicketIF ticket, LocalDateTime time) {
        ticket.bezahlen(time,einnahmen);
    }

    @Override
    public void einzahlen(long summe) {
        guthaben += summe;
    }

    @Override
    public long getGuthaben() {
        return guthaben;
    }

    @Override
    public EinnahmenIF getEinnahmen() {
        return this.einnahmen;
    }


}
