package services;

import models.Ticket;
import models.TicketIF;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parkhaus implements ParkhausIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze; // Anzahl freier Plaetze
    private final int kapazitaet; // Die Gesamtzahl der vorhandenen Parkplätze
    private SchrankeIF[] schranken; // Alle verfügbaren Schranken
    private List<TicketIF> ticketListe = new LinkedList<>();
    private BezahlautomatIF automat = new Bezahlautomat();

    /**
     * Konstruktur, der ein Parkhaus mit 100 Parkplaetzen und zwei Ein - sowie Ausfahrtsschranken initialisiert.
     */
    public Parkhaus(){
        this.freiePlaetze = 100;
        this.kapazitaet = 100;
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
        this.kapazitaet = kapazitaet;
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
            Ticket ticket = new Ticket(id++, 2); // erstellt models.Ticket

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
    public Ticket einfahrt(SchrankeIF schranke, int preis) {
        if (this.freiePlaetze > 0) {
            Ticket ticket = new Ticket(id++, preis); // erstellt models.Ticket

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
                ticket.getZahlungsZeit().isAfter(ticket.getAusfahrtsZeit().minus(Duration.ofMinutes(15)))) {
            schranke.open();
            schranke.close();
            ticket.setGueltigkeit(false);
            this.freiePlaetze++;
            return true;
        }
        ticket.setAusfahrtsZeit(null);
        return false;
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
    private TicketIF[] getTicketListe(String bezahltUnbezahlt) {
        List<TicketIF> tickets = new LinkedList<>();
        for (TicketIF ticket: this.ticketListe){ // für jedes existierendes Ticket
            if (ticket.istGueltig()){ // muss das Ticket gültig sein
                if (ticket.istBezahlt() && "bezahlt".equals(bezahltUnbezahlt)){ // falls die bezahlten gesucht sind
                    tickets.add(ticket);
                } else if (!ticket.istBezahlt() && "unbezahlt".equals(bezahltUnbezahlt)){ // falls die unbezahlten gesucht sind
                    tickets.add(ticket);
                }
            }
        }
        return tickets.toArray(new TicketIF[tickets.size()]);
    }

    @Override
    public int getAnzahlFreiePlaetze() {
        return this.freiePlaetze;
    }

    @Override
    public TicketIF getTicket(int id){
        return this.ticketListe.get(id - 1);
    }

    @Override
    public BezahlautomatIF getAutomat() {
        return automat;
    }

    @Override
    public int getKapazitaet() {
        return kapazitaet;
    }

    @Override
    public SchrankeIF[] getEinfahrtSchranken() {
        return getSchranken("einfahrt");
    }

    @Override
    public SchrankeIF[] getAusfahrtSchranken() {
        return getSchranken("ausfahrt");
    }

    @Override
    public TicketIF[] getBezahlteTickets(){
        return getTicketListe("bezahlt");
    }

    @Override
    public TicketIF[] getUnbezahlteTickets(){
        return getTicketListe("unbezahlt");
    }

    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }
}
