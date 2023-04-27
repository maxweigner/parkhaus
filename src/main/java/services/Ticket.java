package services;

import java.time.LocalDateTime;

public class Ticket implements TicketIF{
    private final int ID;
    private final int preis;
    private LocalDateTime einfahrtZeit;

    private LocalDateTime ausfahrtZeit;
    private boolean bezahlt = false;
    private boolean gueltig = true;

    /**
     *
     * @param ID Identifikationsnummer des Tickets
     * @param preis Preis des Tickets
     */
    public Ticket(int ID, int preis){
        this.ID = ID;
        this.preis = preis;
        this.einfahrtZeit = LocalDateTime.now();
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
    public LocalDateTime getZeit() {
        return this.einfahrtZeit;
    }


    @Override
    public void setZeit(LocalDateTime time) {
        this.einfahrtZeit = time;
    }

    @Override
    public void setBezahlt() {
        this.bezahlt = true;
    }

    @Override
    public boolean istBezahlt() {
        return this.bezahlt;
    }
    public LocalDateTime getAusfahrtZeit() {
        return ausfahrtZeit;
    }

    public void setAusfahrtZeit(LocalDateTime ausfahrtZeit) {
        this.ausfahrtZeit = ausfahrtZeit;
    }

    public void setGueltigkeit(boolean boo){
        this.gueltig = boo;
    }

    public boolean istGueltig(){
        return this.gueltig;
    }
    public String toString(){
        return "Ticket Nr: " + this.getID() + " Einfahrtzeit: " + this.getZeit() + " Ausfahrtzeit: :" + getAusfahrtZeit();
    }


}
