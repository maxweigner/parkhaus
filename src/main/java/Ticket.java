import java.time.LocalDateTime;

public class Ticket implements TicketIF{
    private int ID;
    private int preis;
    private LocalDateTime einfahrtZeit;
    private boolean bezahlt = false;

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
        bezahlt = true;
    }

    @Override
    public boolean istBezahlt() {
        return bezahlt;
    }
}
