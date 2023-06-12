package services;

import models.Ticket;
import models.TicketIF;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class Parkhaus implements ParkhausIF {
    private int id = 1; // laufende ID zur Vergabe bei neuen Tickets
    private int freiePlaetze; // Anzahl freier Plaetze
    private final int kapazitaet; // Gesamtzahl der vorhandenen Parkplätze
    private SchrankeIF[] schranken; // alle verfügbaren Schranken
    private List<TicketIF> ticketListe = new LinkedList<>();
    private BezahlautomatIF automat = new Bezahlautomat();
    private LocalDateTime aktuelleZeit;

    /**
     * Konstruktur, der ein Parkhaus mit 100 Parkplaetzen und zwei Ein - sowie Ausfahrtsschranken initialisiert.
     */
    public Parkhaus(){
        freiePlaetze = 100;
        kapazitaet = 100;
        schranken = new Schranke[2];
        schranken[0] = new Schranke();
        schranken[0].setSchranke("einfahrt");
        schranken[1] = new Schranke();
        schranken[1].setSchranke("ausfahrt");
        aktuelleZeit = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
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
        this.aktuelleZeit = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
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
            Ticket ticket = new Ticket(id++, preis); // erstellt Ticket

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
    public Ticket einfahrt(SchrankeIF schranke, int preis, LocalDateTime aktuelleZeit) {
        if (this.freiePlaetze > 0) {
            Ticket ticket = new Ticket(id++, preis, LocalDateTime.parse(aktuelleZeit.toString(), ISO_LOCAL_DATE_TIME)); // erstellt models.Ticket

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
    public void einfahrt(SchrankeIF schranke, TicketIF monatsticket) {
        if (this.freiePlaetze > 0) {
            // Schranke auf/zu
            schranke.open();
            schranke.close();

            monatsticket.setGueltigkeit(true);
            this.freiePlaetze--; // freie Plätze anpassen
        }
    }

    @Override
    public boolean ausfahrt(TicketIF ticket, SchrankeIF schranke) {
        boolean versuch = ticket.ausfahren(schranke, aktuelleZeit);
        if (versuch) { // Ausfahrt erfolgreich
            this.freiePlaetze++;
            return true;
        }
        return false;
    }

    public Ticket erstelleMonatsticket(int preis, LocalDateTime aktuelleZeit){
        Ticket ticket = new Ticket(id++, preis); // erstellt Ticket
        ticket.setEinfahrtsZeit(LocalDateTime.parse(aktuelleZeit.toString(), ISO_LOCAL_DATE_TIME));
        ticket.setMonatsTicket(true);
        ticket.setGueltigkeit(false);
        this.ticketListe.add(ticket);
        return ticket; // ticket ausgeben
    }

    @Override
    public void startLaden(TicketIF ticket) {
        ticket.startAufladen(aktuelleZeit);
    }

    @Override
    public void stopLaden(TicketIF ticket, int stundenPreis) {
        ticket.endAufladen(aktuelleZeit,stundenPreis);
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
        Stream<SchrankeIF> schrankenStream = Arrays.stream(schranken);
        return schrankenStream.filter(s -> s.getSchranke().equals("einfahrt")). // alle Einfahrtsschranken
                toArray(SchrankeIF[]::new);
    }

    @Override
    public SchrankeIF[] getAusfahrtSchranken() {
        Stream<SchrankeIF> schrankenStream = Arrays.stream(schranken);
        return schrankenStream.filter(s -> s.getSchranke().equals("ausfahrt")). // alle Ausfahrtsschranken
                toArray(SchrankeIF[]::new);
    }

    @Override
    public TicketIF[] getBezahlteTickets(){
        Stream<TicketIF> tickets = ticketListe.stream();
        return tickets.filter(t -> t.istGueltig()). // befindet sich im Parkhaus
                filter(t -> t.istBezahlt()). // und hat gezahlt
                toArray(Ticket[]::new);
    }

    @Override
    public TicketIF[] getUnbezahlteTickets() {
        Stream<TicketIF> tickets = ticketListe.stream();
        return tickets.filter(t -> t.istGueltig()). // befindet sich im Parkhaus
                filter(t -> !t.istBezahlt()). // und hat nicht gezahlt
                toArray(Ticket[]::new);
    }

    @Override
    public TicketIF[] getLadendeTickets(){
        Stream<TicketIF> tickets = ticketListe.stream();
        return tickets.filter(t -> t.getStartLadeZeit() != null). // Startstempel muss vorhanden sein
                filter(t -> t.getLadend()). // ladend
                toArray(Ticket[]::new);
    }

    @Override
    public TicketIF[] getNichtLadendeTickets(){
        Stream<TicketIF> tickets = ticketListe.stream();
        return tickets.filter(t -> !t.getLadend()). // nicht ladend
                filter(t -> !t.istBezahlt()). // nicht bezahlt
                toArray(TicketIF[]::new);
    }

    @Override
    public TicketIF[] getMonatstickets(){
        Stream<TicketIF> tickets = ticketListe.stream();
        return tickets.filter(t -> t.isMonatsTicket()).
                filter(t -> !t.istGueltig()).
                toArray(TicketIF[]::new);
    }

    @Override
    public LocalDateTime getAktuelleZeit() {
        return aktuelleZeit;
    }

    @Override
    public void setAnzahlPlaetze(int plaetze) {
        this.freiePlaetze = plaetze;
    }

    @Override
    public boolean setAktuelleZeit(LocalDateTime now) {
        if (now.isAfter(aktuelleZeit)) {
            aktuelleZeit = now;
            return true;
        }
        return false;
    }
}
