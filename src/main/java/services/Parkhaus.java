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
    private BezahlautomatIF automat = new Bezahlautomat();

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

    /**
     * Konstruktur mit individuellen Einstellungen
     * @param kapazitaet: gesamte Anzahl an verfügbaren Parkplätzen
     * @param anzahlEinfahrtSchranken: Anzahl an Einfahrtsschranken
     * @param anzahlAusfahrtSchranken: Anzahl an Aussfahrtsschranken
     */
    public Parkhaus(int kapazitaet, int anzahlEinfahrtSchranken, int anzahlAusfahrtSchranken){
        this.freiePlaetze = kapazitaet;
        int anzahlSchranken = anzahlEinfahrtSchranken + anzahlAusfahrtSchranken; // Summe der Schranken
        this.schranken = new SchrankeIF[anzahlSchranken];
        for (int i = 0; i < anzahlSchranken; i++){
            SchrankeIF schranke = new Schranke();
            if (i < anzahlEinfahrtSchranken){ // die ersten Schranken sind Einfahrtsschranken
                schranke.setSchranke("einfahrt");
            } else { // die darauffolgenden sind Ausfahrtsschranken
                schranke.setSchranke("ausfahrt");
            }
            this.schranken[i] = schranke;
        }
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
            if (s.getSchranke().equals(einfahrtAusfahrt)) {
                schrankenList.add(s);
            }
        }
        return schrankenList.toArray(new SchrankeIF[schrankenList.size()]);
    }

    /**
     * Von allen Tickets werden die bezahlten herausgegeben
     * @return Ticketliste mit allen bezahlten Tickets
     */
    @Override
    public TicketIF[] getBezahlteTickets() {
        List<TicketIF> bezahlteTickets = new LinkedList<>();
        for (TicketIF ticket: this.ticketListe){ // für jedes existierendes Ticket
            if (ticket.istBezahlt()){ // falls das Ticket bezahlt ist
                bezahlteTickets.add(ticket);
            }
        }
        return bezahlteTickets.toArray(new TicketIF[bezahlteTickets.size()]);
    }

    @Override
    public int getAnzahlFreiePlaetze() {
        return this.freiePlaetze;
    }

    public TicketIF getTicket(int id){
        return this.ticketListe.get(id - 1);
    }

    @Override
    public BezahlautomatIF getAutomat() {
        return automat;
    }
}
