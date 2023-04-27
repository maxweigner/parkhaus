package services;

import java.time.Duration;
import java.time.LocalDateTime;

public class Bezahlautomat implements BezahlautomatIF {

    private long guthaben = 0;
    private EinnahmenIF einnahmen = new Einnahmen();

    @Override
    public boolean bezahlen(TicketIF ticket) {
        // parkdauer in stunden * preis des tickets
        int preis = (int)Duration.between(ticket.getZeit(), LocalDateTime.now()).toHours() * ticket.getPreis();

        if (guthaben >= preis) {
            ticket.setZeit(LocalDateTime.now());
            guthaben -= preis;
            ticket.setBezahlt();

            einnahmen.addIncome(preis);

            return true;
        }

        return false;
    }

    @Override
    public boolean bezahlen(TicketIF ticket, LocalDateTime now) {
        // berechnet kosten mit parkdauer in stunden * preis des tickets
        int preis = (int)Duration.between(ticket.getZeit(), now).toHours() * ticket.getPreis();
        ticket.setAusfahrtZeit(now);
        //ticket.setZeit(now);
        ticket.setBezahlt();
        //System.out.println(ticket);
        //ticket.setGesamtpreis(preis);
        einnahmen.addIncome(preis);

        return true;
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
