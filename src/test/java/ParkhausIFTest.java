import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    ParkhausIF parkhaus;
    BezahlautomatIF automat;

    @BeforeEach
    void setUp(){
        parkhaus = new Parkhaus();
        automat = new Bezahlautomat();
    }
    @DisplayName("Unterschiedliche Tickets haben unterschiedliche IDs")
    @Test
    void ticketErstellenTest() {
        TicketIF ticket1 = parkhaus.erstellen();
        TicketIF ticket2 = parkhaus.erstellen();
        assertNotEquals(ticket1.getID(), ticket2.getID());
    }

    @DisplayName("Automat erkennt bezahlte Tickets")
    @Test
    void ticketEntwertenTest() {
        TicketIF ticket1 = parkhaus.erstellen();
        assertFalse(SchrankeIF.ausfahrt(ticket1, new Schranke()));
        automat.bezahlen(ticket1);
        assertTrue(parkhaus.entwerten(ticket1));
    }

    @Test
    @DisplayName("Die Anzahl der freien Plätze kann gesetzt und abgerufen werden")
    void setSpacesAmount() {
        parkhaus = new Parkhaus();
        parkhaus.setSpacesAmount(1500);
        assertEquals(1500, parkhaus.freeSpaces());
    }


    @Test
    @DisplayName("Anzahl freier Plätze wird bei Einfahrt reduziert")
    void einfahrt() {
        parkhaus = new Parkhaus();
        parkhaus.setSpacesAmount(1500);
        parkhaus.einfahrt();

        assertEquals(1499, parkhaus.freeSpaces());
    }

}