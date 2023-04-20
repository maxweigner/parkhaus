package services;

import java.time.Duration;
import java.time.LocalDateTime;

public class Bezahlautomat implements BezahlautomatIF {

    private long guthaben = 0;

    @Override
    public boolean bezahlen(TicketIF ticket) {
        // parkdauer in stunden * preis des tickets
        int preis = (int)Duration.between(ticket.getZeit(), LocalDateTime.now()).toHours() * ticket.getPreis();

        if (guthaben >= preis) {
            ticket.setZeit(LocalDateTime.now());
            guthaben -= preis;
            ticket.setBezahlt();

            return true;
        }

        return false;
    }

    @Override
    public void einzahlen(long summe) {
        guthaben += summe;
    }

    @Override
    public long getGuthaben() {
        return guthaben;
    }

}
