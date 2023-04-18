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
        assertFalse(SchrankeIF.ausfahrt(ticket1, new Schranke()));
        automat.bezahlen(ticket1);
        assertTrue(automat.entwerten(ticket1));
    }

    @Test
    @DisplayName("Die Anzahl der freien Plätze kann gesetzt und abgerufen werden")
    void setSpacesAmount() {
        automat = new Parkautomat();
        automat.setSpacesAmount(1500);
        assertEquals(1500, automat.freeSpaces());
    }


    @Test
    @DisplayName("Anzahl freier Plätze wird bei Einfahrt reduziert")
    void einfahrt() {
        automat = new Parkautomat();
        automat.setSpacesAmount(1500);
        automat.einfahrt();

        assertEquals(1499, automat.freeSpaces());
    }

}