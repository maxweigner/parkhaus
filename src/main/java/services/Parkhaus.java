package services;

import models.Ticket;
import models.TicketIF;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class Parkhaus implements ParkhausIF {
    private static int id = 1; // laufende ID zur Vergabe bei neuen Tickets
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
        if (ticket.istBezahlt() && ticket.istGueltig() &&
                ticket.getZahlungsZeit().isAfter(ticket.getAusfahrtsZeit().minus(Duration.ofMinutes(15)))) {
            schranke.open();
            schranke.close();
            ticket.setGueltigkeit(false);
            if(ticket.isMonatsTicket()){
                if(!ticket.getAusfahrtsZeit().isBefore(ticket.getEinfahrtsZeit().plusMonths(1))){
                    ticket.setGueltigkeit(true);
                    ticket.setBezahlung(true);
                    throw new IllegalStateException("Ticket wurde überzogen, Betreiber kontaktieren");
                    //return false;
                }
                ticket.setBezahlung(false);
                this.freiePlaetze++;
                ticket.setGueltigkeit(true);
                ticket.setGesamtpreis(0);
                return true;
            }
            ticket.setAusfahrtsZeit(LocalDateTime.parse(aktuelleZeit.toString(), ISO_LOCAL_DATE_TIME));
            this.freiePlaetze++;

            return true;
        }
        ticket.setAusfahrtsZeit(null);
        return false;
    }

    @Override
    public void startLaden(TicketIF ticket) {
        ticket.setStartLadeZeit(aktuelleZeit);
    }

    @Override
    public void stopLaden(TicketIF ticket, int stundenPreis) {
        long ladeStunden = ticket.getStartLadeZeit().until(aktuelleZeit, ChronoUnit.HOURS);
        ticket.setGesamtpreis(ticket.getGesamtpreis() + stundenPreis * ((int) ladeStunden));
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
                    if (ticket.getStartLadeZeit() == null)
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
        LinkedList<TicketIF> tickets = new LinkedList<>(ticketListe);

        Iterator<TicketIF> itr = tickets.iterator();

        if (itr.hasNext()) {
            do {
                TicketIF ticket = itr.next();
                if (ticket.getStartLadeZeit() == null || !ticket.istGueltig()) {
                    itr.remove();
                }
            } while (itr.hasNext());
        }

        return tickets.toArray(new TicketIF[tickets.size()]);
    }

    @Override
    public TicketIF[] getNichtLadendeTickets(){
        LinkedList<TicketIF> tickets = new LinkedList<>(Arrays.asList(getUnbezahlteTickets()));
        tickets.removeAll(new LinkedList<>(Arrays.asList(getLadendeTickets())));
        return tickets.toArray(new TicketIF[tickets.size()]);
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

    @Override
    public LocalDateTime getAktuelleZeit() {
        return aktuelleZeit;
    }

}
