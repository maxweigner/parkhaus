
import java.util.Random;

public class Ticket implements TicketIF{
    private int ID;
    private int preis;
    private TimeIF einfahrtZeit;
    private TimeIF ausfahrtZeit;

    public Ticket(int ID, int preis, TimeIF einfahrtZeit, TimeIF ausfahrtZeit){
        this.ID = ID;
        this.preis = preis;
        this.einfahrtZeit = einfahrtZeit;
        this.ausfahrtZeit = ausfahrtZeit;
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
    public TimeIF getStart() {
        return this.einfahrtZeit;
    }

    @Override
    public TimeIF getEnde() {
        return this.ausfahrtZeit;
    }

    @Override
    public void setEnde(TimeIF time) {
        this.ausfahrtZeit = time;
    }
}
