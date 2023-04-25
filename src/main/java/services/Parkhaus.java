package services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Parkhaus implements ParkhausIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze; // Anzahl freier Plaetze
    private Schranke[] schranken; // Alle verfügbaren Schranken

    /**
     * Konstruktur, der ein services.Parkhaus mit 100 Parkplaetzen und zwei Ein - sowie Ausfahrtsschranken initialisiert.
     */
    public Parkhaus(){
        this.freiePlaetze = 100;
        this.schranken = new Schranke[2];
        this.schranken[0] = new Schranke();
        this.schranken[0].setSchranke("einfahrt");
        this.schranken[1] = new Schranke();
        this.schranken[1].setSchranke("ausfahrt");
    }

    @Override
    public Ticket einfahrt(SchrankeIF schranke) {
        if (this.freiePlaetze > 0) {
            Ticket ticket = new Ticket(id++, 2); // erstellt services.Ticket

            // services.Schranke auf/zu
            schranke.open();
            schranke.close();

            this.freiePlaetze--; // freie Plätze anpassen

            return ticket; // ticket ausgeben
        }
        return null;
    }

    @Override
    public boolean ausfahrt(TicketIF ticket, SchrankeIF schranke) {
        if (ticket.istBezahlt() &&
                ticket.getZeit().isAfter(LocalDateTime.now().minus(Duration.ofMinutes(15)))) {
            schranke.open();
            schranke.close();

            this.freiePlaetze++;

            return true;
        }
        return false;
    }

    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }

    @Override
    public Schranke[] getEinfahrtSchranken() {
        return getSchranken("einfahrt");
    }

    @Override
    public Schranke[] getAusfahrtSchranken() {
        return getSchranken("ausfahrt");
    }


    /**
     * Gibt ein Array mit Schranken aus die vom angegebenen Typ sind
     * @param einfahrtAusfahrt Der Typ der Schranken. Entweder "einfahrt" oder "ausfahrt"
     * @return Array mit Schranken
     */
    private Schranke[] getSchranken(String einfahrtAusfahrt) {
        ArrayList<Schranke> schrankenList = new ArrayList<Schranke>();
        int i = 0;
        for(Schranke s: schranken) {
            if(s.getSchranke().equals(einfahrtAusfahrt)){
                schrankenList.add(s);
                i++;
            }
        }

        return schrankenList.toArray(new Schranke[i]);
    }

    @Override
    public int getAnzahlFreiePlaetze() {
        return this.freiePlaetze;
    }
}
