package services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parkhaus implements ParkhausIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze; // Anzahl freier Plaetze
    private SchrankeIF[] schranken; // Alle verfügbaren Schranken

    private List<TicketIF> ticketListe = new LinkedList<>();

    /**
     * Konstruktur, der ein Parkhaus mit 100 Parkplaetzen und zwei Ein - sowie Ausfahrtsschranken initialisiert.
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

            // Schranke auf/zu
            schranke.open();
            schranke.close();

            this.freiePlaetze--; // freie Plätze anpassen
            this.ticketListe.add(ticket);

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
            return true;
        }
        return false;
    }

    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }

    @Override
    public SchrankeIF[] getEinfahrtSchranken() {
        return getSchranken("einfahrt");
    }

    @Override
    public SchrankeIF[] getAusfahrtSchranken() {
        return getSchranken("ausfahrt");
    }


    /**
     * Gibt ein Array mit Schranken aus die vom angegebenen Typ sind
     * @param einfahrtAusfahrt Der Typ der Schranken. Entweder "einfahrt" oder "ausfahrt"
     * @return Array mit Schranken
     */
    private SchrankeIF[] getSchranken(String einfahrtAusfahrt) {
        List<SchrankeIF> schrankenList = new ArrayList<>();
        for(SchrankeIF s: schranken) {
            if(s.getSchranke().equals(einfahrtAusfahrt))
                schrankenList.add(s);
        }

        return schrankenList.toArray(new SchrankeIF[10]);
    }

    @Override
    public int getAnzahlFreiePlaetze() {
        return this.freiePlaetze;
    }

    public List<TicketIF> getTicketListe(){
        return this.ticketListe;
    }
}
