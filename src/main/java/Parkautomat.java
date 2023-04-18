import java.util.Random;

public class Parkautomat implements ParkautomatIF {
    static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    static int revenue = 0; // totale Einnahmen
    Random rd = new Random();

    /**
     * Erstellt ein neues Ticket zum Zeitpunkt der Einfahrt
     * @return Ticket
     */
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

    /**
     * Zahlungsvorgang, um mit einem Ticket die Schranke zu öffnen
     * @param ticket: gültiges Ticket
     */
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
}
