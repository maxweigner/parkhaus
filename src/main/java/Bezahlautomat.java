import java.util.Random;

public class Bezahlautomat implements BezahlautomatIF {
    private Random rd = new Random();

    @Override
    public void bezahlen(TicketIF ticket) {
        // zufällige Ausfahrtszeit
        int hours = rd.nextInt(24);
        int minutes = rd.nextInt(60);
        TimeIF now = new Time(hours, minutes); // erstellt Zeitstempel

        ticket.setEnde(now);
        int preis = (ticket.getEnde().getHours() - ticket.getStart().getHours()) * ticket.getPreis();
    }
}
