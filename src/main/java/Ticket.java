public class Ticket implements TicketIF{
    private int id;
    private int preis;
    private TimeIF start;
    private TimeIF ende;

    public Ticket(int id, int preis, TimeIF start){
        this.id = id;
        this.preis = preis;
        this.start = start;
    }
    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getPreis() {
        return this.preis;
    }

    @Override
    public TimeIF getStart() {
        return this.start;
    }

    @Override
    public TimeIF getEnde() {
        return this.ende;
    }

    public void setEnde(TimeIF ende) { this.ende = ende;}
}
