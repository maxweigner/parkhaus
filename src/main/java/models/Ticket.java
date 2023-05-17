package models;

import java.time.LocalDateTime;

public class Ticket implements TicketIF{
    private final int ID;
    private int preis;
    private LocalDateTime einfahrtsZeit;

    private LocalDateTime zahlungsZeit;
    private LocalDateTime ausfahrtsZeit;
    private boolean bezahlt = false;
    private boolean gueltig = true;
    private int gesamtpreis = 0;
    private LocalDateTime startLadeZeit;

    /**
     * @param ID Identifikationsnummer des Tickets
     * @param preis Preis des Tickets
     */
    public Ticket(int ID, int preis){
        this.ID = ID;
        this.preis = preis;
        this.einfahrtsZeit = LocalDateTime.now();
        this.startLadeZeit = null;
    }

    public int getGesamtpreis(){
        return this.gesamtpreis;
    }

    public void setGesamtpreis(int gesamtpreis){
        this.gesamtpreis = gesamtpreis;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public int getPreis() {
        return this.preis;
    }

    @Override
    public void setPreis(int preis) {
        this.preis = preis;
    }

    @Override
    public LocalDateTime getEinfahrtsZeit() {
        return this.einfahrtsZeit;
    }

    @Override
    public LocalDateTime getZahlungsZeit() {
        return this.zahlungsZeit;
    }

    @Override
    public LocalDateTime getAusfahrtsZeit() {
        return this.ausfahrtsZeit;
    }

    @Override
    public void setEinfahrtsZeit(LocalDateTime time) {
        this.einfahrtsZeit = time;
    }

    @Override
    public void setZahlungsZeit(LocalDateTime time) {
        this.zahlungsZeit = time;
    }

    @Override
    public void setAusfahrtsZeit(LocalDateTime time) {
        this.ausfahrtsZeit = time;
    }

    @Override
    public void setBezahlt() {
        this.bezahlt = true;
    }

    @Override
    public boolean istBezahlt() {
        return this.bezahlt;
    }

    public void setGueltigkeit(boolean boo){
        this.gueltig = boo;
    }


    public boolean istGueltig(){
        return this.gueltig;
    }

    public void setStartLadeZeit(LocalDateTime start) {
        this.startLadeZeit = start;
    }

    public LocalDateTime getStartLadeZeit() {
        return startLadeZeit;
    }

    @Override
    public String toString(){
        return "Ticket Nr: " + this.getID() + " Einfahrtzeit: " + this.getEinfahrtsZeit() + " Ausfahrtzeit: " + getAusfahrtsZeit();
    }


}
