package services;

import models.TicketIF;

public class Ladestation implements LadestationIF {
    TicketIF ticket;
    String anbieter;
    boolean belegt;

    public Ladestation(String anbieter){
        this.ticket = null;
        this.anbieter = anbieter;
        this.belegt = false;
    }

    @Override
    public boolean startLaden(TicketIF ticket) {
        if (this.anbieter != null && this.belegt == false){ // Ladestation am Stromnetz
            this.ticket = ticket; // Ticket zur Abrechnung
            this.belegt = true; // pro Station nur ein Fahrzeug
            return true;
        }
        return false; // Ladevorgang konnte nicht gestartet werden
    }

    @Override
    public boolean stopLaden(TicketIF ticket) {
        if (this.ticket != null){ // Ladestation wird genutzt
            this.ticket = null; // Ticket wird abgerechnet
            //TODO Strompreis inkludieren
            this.belegt = false; // pro Station nur ein Fahrzeug
            return true;
        }
        return false; // Ladevorgang konnte nicht gestoppt werden
    }

    @Override
    public int getAbrechnungsnummer() {
        return this.ticket.getID();
    }

    @Override
    public boolean getBelegt() {
        return this.belegt;
    }

    @Override
    public TicketIF getTicket() {
        return this.ticket;
    }
}
