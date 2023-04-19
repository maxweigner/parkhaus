import java.util.Random;

public class Parkhaus implements ParkhausIF {
    private Random rd = new Random();
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze = 0; // Anzahl freier Plaetze
    private Schranke[] schranken = null; // Alle verfügbaren Schranken

    /**
     * Konstruktur, der ein Parkhaus mit 100 Parkplaetzen und zwei Ein - sowie Ausfahrtsschranken initialisiert.
     */
    Parkhaus(){
        this.freiePlaetze = 100;
        this.schranken = new Schranke[2];
        this.schranken[0] = new Schranke();
        this.schranken[0].setSchranke("einfahrt");
        this.schranken[1] = new Schranke();
        this.schranken[1].setSchranke("ausfahrt");
    }

    @Override
    public TicketIF einfahrt() {
        // zufällige Einfahrtszeit
        int hours = rd.nextInt(24);
        int minutes = rd.nextInt(60);

        if (this.freiePlaetze > 0) {
            TimeIF now = new Time(hours, minutes); // erstellt Zeitstempel
            TicketIF ticket = new Ticket(id++, 2, now, null); // erstellt Ticket
            this.schranken[0].open();
            this.schranken[1].close();
            this.freiePlaetze--;
            return ticket;
        }
        return null;
    }

    @Override
    public boolean ausfahrt(TicketIF ticket) {
        if (ticket == null) {return false;}
        if (ticket.getEnde() == null){
            return false;
        }
        ticket = null;
        this.schranken[1].open();
        this.schranken[1].close();
        this.freiePlaetze++;
        return true;
    }

    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }
    @Override
    public int getAnzahlFreiePlaetze() {
        return this.freiePlaetze;
    }
}
