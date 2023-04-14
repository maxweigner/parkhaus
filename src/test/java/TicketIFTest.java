import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketIFTest {
    TicketIF ticket;
    @BeforeEach
    void setup(){
        ticket = new Ticket(1, 2, new Time(12,0), new Time(13,0));
    }
    @Test
    void getIDTest() {
        int eID = 1;
        int id = ticket.getID();
        assertEquals(eID, id);
    }

    @Test
    void getPreisTest() {
        int ePreis = 2;
        int preis = ticket.getPreis();
        assertEquals(ePreis, preis);
    }

    @Test
    void getStartTest() {
        TimeIF eTime = new Time(12,0);
        TimeIF t1 = ticket.getStart();
        assertEquals(t1.toString(), eTime.toString());
    }

    @Test
    void getEndeTest() {
        TimeIF eTime = new Time(13,0);
        TimeIF t1 = ticket.getEnde();
        assertEquals(t1.toString(), eTime.toString());
    }

    @Test
    void setEndeTest(){
        Ticket tmp = new Ticket(1,2,new Time(12,1), null);
        Time t = new Time(13,1);
        tmp.setEnde(t);
        assertEquals(t.toString(), tmp.getEnde().toString());
    }
}