import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Ticket;
import services.TicketIF;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketIFTest {
    TicketIF ticket;
    LocalDateTime zeit;
    @BeforeEach
    void setup(){
        ticket = new Ticket(1, 2);
        zeit = LocalDateTime.now();
        ticket.setZeit(zeit);
    }
    @Test
    void getIDTest() {
        assertEquals(1, ticket.getID());
        ticket = new Ticket(2, 2);
        assertEquals(2, ticket.getID());
    }

    @Test
    void getPreisTest() {
        int ePreis = 2;
        int preis = ticket.getPreis();
        assertEquals(ePreis, preis);
    }

    @Test
    void getZeitTest() {
        assertTrue(zeit.isEqual(ticket.getZeit()));
    }

    @Test
    void setZeitTest(){
        LocalDateTime zeit = LocalDateTime.now();
        ticket.setZeit(zeit);
        assertEquals(zeit, ticket.getZeit());
    }
}