package services;

import models.Ticket;
import models.TicketIF;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Parkhaus implements ParkhausIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze; // Anzahl freier Plaetze
    private final int kapazitaet; // Gesamtzahl der vorhandenen Parkplätze
    private SchrankeIF[] schranken; // alle verfügbaren Schranken

    private LadestationIF[] ladestationen; // alle existierenden Ladestationen
    private LadestationIF[] belegteLadestationen; // alle belegten Ladenstationen

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

        int stationen = 10; // Anzahl an Ladestationen
        this.ladestationen = new Ladestation[stationen];
        for (int i = 0; i < stationen; i++){
            this.ladestationen[i] = new Ladestation("Green Energy", i+1);
        }
        this.belegteLadestationen = new LadestationIF[stationen]; // Initialisierung
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

    @Override
    public void startLaden(TicketIF ticket) {
        LadestationIF ls = getLadestationen("frei")[0];
        boolean starten = ls.startLaden(ticket);

        if (!starten){ // Fehler beim Starten
            System.out.println("Ladevorgang mit Abrechnungsnnummer: " +
                    ticket.getID() + " konnte nicht gestartet werden");
        } else {
            this.belegteLadestationen[ls.getSeriennummer() - 1] = ls; // Ladestation wird als belegt registriert
        }
    }

    @Override
    public void stopLaden(TicketIF ticket) {
        boolean stoppen = false;
        for (LadestationIF ls: belegteLadestationen){ // für jede belegte Ladestation
            if (ls != null && ls.getAbrechnungsnummer() == ticket.getID()){ // wird diejenige mit übergebenen Ticket gesucht
                stoppen = ls.stopLaden(ticket);
                if (stoppen) { // falls der Ladevorgang erfolgreich beendet wurde
                    belegteLadestationen[ls.getSeriennummer() - 1] = null; // ist die Ladestation nicht mehr belegt
                }
                break;
            }
        }

        if (!stoppen){ // Fehler beim Stoppen
            System.out.println("Ladevorgang mit Abrechnungsnnummer: " +
                    ticket.getID() + " konnte nicht gestoppt werden");
        }
    }

    /**
     * Gibt ein Array mit Schranken aus, die vom angegebenen Typ sind
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
     * Gibt ein Array mit Ladestationen aus, die sich im ausgewählten Status befinden
     * @param freiBelegt: Status der Ladestation. Entweder "frei" oder "belegt"
     * @return Array mit Ladestationen
     */
    private LadestationIF[] getLadestationen(String freiBelegt){
        List<LadestationIF> ladestationenListe = new ArrayList<>();
        boolean suche = false; // freie Ladestationen
        if (freiBelegt.equals("belegt")){ // falls gefordert
            suche = true; // werden die belegten Stationen gesucht
        }
        for(LadestationIF ls: ladestationen) {
            if (suche == ls.getBelegt()) {
                ladestationenListe.add(ls);
            }
        }
        return ladestationenListe.toArray(new LadestationIF[ladestationenListe.size()]);
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
    public TicketIF[] getLadendeTickets(){
        LadestationIF[] ls = getLadestationen("belegt");
        int size = ls.length;
        TicketIF[] tickets = new TicketIF[size];

        for (int i = 0; i < size; i++){
            tickets[i] = ls[i].getTicket();
        }
        return tickets;
    }

    @Override
    public TicketIF[] getNichtLadendeTickets(){
        List<TicketIF> tickets = new LinkedList<>(ticketListe); // kopiere Ticketliste
        tickets.removeAll(Arrays.asList(getLadendeTickets()));
        return tickets.toArray(new TicketIF[tickets.size()]);
    }


    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }
}
