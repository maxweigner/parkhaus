import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkautomatIFTest {
    ParkautomatIF automat;

    @BeforeEach
    void setUp(){
        automat = new Parkautomat();
    }
    @DisplayName("Unterschiedliche Tickets haben unterschiedliche IDs")
    @Test
    void ticketErstellenTest() {
        TicketIF ticket1 = automat.erstellen();
        TicketIF ticket2 = automat.erstellen();
        assertNotEquals(ticket1.getID(), ticket2.getID());
    }

    @DisplayName("Nach Bezahlvorgang wird Endstempel zur Berechtigung der Ausfahrt eingetragen")
    @Test
    void ticketBezahlenTest() {
        TicketIF ticket1 = automat.erstellen();
        automat.bezahlen(ticket1);
        assertNotEquals(null, ticket1.getEnde());
    }

    @DisplayName("Automat erkennt bezahlte Tickets")
    @Test
    void ticketEntwertenTest() {
        TicketIF ticket1 = automat.erstellen();
        assertFalse(automat.entwerten(ticket1));
        automat.bezahlen(ticket1);
        assertTrue(automat.entwerten(ticket1));
    }
}