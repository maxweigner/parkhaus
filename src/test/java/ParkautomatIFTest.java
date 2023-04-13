import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkautomatIFTest {
    ParkautomatIF automat;

    @BeforeEach
    void setUp(){
        automat = new Parkautomat();
    }
    @Test
    void erstellenTest() {
        TicketIF ticket1 = automat.erstellen();
        TicketIF ticket2 = automat.erstellen();
        assertNotEquals(ticket1.getID(), ticket2.getID());
    }

    @Test
    void bezahlenTest() {
        TicketIF ticket1 = automat.erstellen();
        automat.bezahlen(ticket1);
        assertNotEquals(null, ticket1.getEnde());
    }

    @Test
    void entwertenTest() {
        TicketIF ticket1 = automat.erstellen();
        assertFalse(automat.entwerten(ticket1));
        automat.bezahlen(ticket1);
        assertTrue(automat.entwerten(ticket1));
    }
}