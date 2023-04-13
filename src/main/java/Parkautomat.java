import java.util.Random;

public class Parkautomat implements ParkautomatIF {
    static int id = 1;

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
    @Override
    public TicketIF erstellen() {
        Random rd = new Random(); // zufällige Einfahrtszeit
        int hours = rd.nextInt(24);
        int minutes = rd.nextInt(60);

        TimeIF now = new Time(hours, minutes); // erstellt Zeitstempel
        TicketIF ticket = new Ticket(id++, 2, now); // erstellt Ticket
        Schranke.setSchranke("einfahrt");
        Schranke.open();
        return ticket;
    }

    @Override
    public boolean bezahlen(TicketIF ticket) {
        return false;
    }

    @Override
    public boolean entwerten() {
        return false;
    }

    @Override
    public int getID() {
        return 0;
    }
}
