import java.util.Random;

public class Parkautomat implements ParkautomatIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private static int revenue = 0; // totale Einnahmen
    private Random rd = new Random();
    private int spacesFreeAmount = 0;

    @Override
    public TicketIF erstellen() {
        // zufällige Einfahrtszeit
        int hours = rd.nextInt(24);
        int minutes = rd.nextInt(60);

        TimeIF now = new Time(hours, minutes); // erstellt Zeitstempel
        TicketIF ticket = new Ticket(id++, 2, now, null); // erstellt Ticket
        Schranke s = new Schranke();
        s.setSchranke("einfahrt");
        s.open();
        s.close();
        return ticket;
    }


    @Override
    public void bezahlen(TicketIF ticket) {
        // zufällige Ausfahrtszeit
        int hours = rd.nextInt(24);
        int minutes = rd.nextInt(60);
        TimeIF now = new Time(hours, minutes); // erstellt Zeitstempel

        ticket.setEnde(now);
        int preis = (ticket.getEnde().getHours() - ticket.getStart().getHours()) * ticket.getPreis();
    }

    @Override
    public boolean entwerten(TicketIF ticket) {
        if (ticket == null) {return false;}

        ticket = null;
        return true;
    }

    @Override
    public void einfahrt() {
        spacesFreeAmount--;
    }

    @Override
    public int freeSpaces() {
        return spacesFreeAmount;
    }

    @Override
    public void setSpacesAmount(int spaces) {
        spacesFreeAmount = spaces;
    }
}
