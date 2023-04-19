import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    private ParkhausIF parkhaus;
    private BezahlautomatIF automat;
    private Random rd = new Random();

    @BeforeEach
    void setUp(){
        parkhaus = new Parkhaus();
        automat = new Bezahlautomat();
    }
    @Test
    @DisplayName("Unterschiedliche Tickets haben unterschiedliche IDs")
    void ticketErstellenTest() {
        TicketIF ticket1 = parkhaus.einfahrt();
        TicketIF ticket2 = parkhaus.einfahrt();
        assertNotEquals(ticket1.getID(), ticket2.getID());
    }

    @Test
    @DisplayName("Automat erkennt bezahlte Tickets")
    void ticketEntwertenTest() {
        TicketIF ticket1 = parkhaus.einfahrt();
        assertFalse(SchrankeIF.ausfahrt(ticket1, new Schranke()));
        automat.bezahlen(ticket1);
        assertTrue(parkhaus.ausfahrt(ticket1));
    }

    @Test
    @DisplayName("Die Anzahl der freien Plätze kann gesetzt und abgerufen werden")
    void setSpacesAmount() {
        parkhaus.setAnzahlPlaetze(1500);
        assertEquals(1500, parkhaus.getAnzahlFreiePlaetze());
    }


    @RepeatedTest(10)
    @DisplayName("Anzahl freier Plätze wird bei Einfahrt reduziert")
    void einfahrt() {
        int parkhausKapazität = rd.nextInt(100, 10000);
        parkhaus.setAnzahlPlaetze(parkhausKapazität);
        int einfahrten = rd.nextInt(1, 100);
        for (int i = einfahrten; 0 < i; i--){
            parkhaus.einfahrt();
        }
        assertEquals(parkhausKapazität - einfahrten, parkhaus.getAnzahlFreiePlaetze());
    }
}
