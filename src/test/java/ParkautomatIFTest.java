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
    }

    @Test
    void entwertenTest() {
    }
}