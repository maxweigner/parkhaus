import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    private ParkhausIF parkhaus;
    private BezahlautomatIF automat;
    private final Random rd = new Random();
    Schranke schranke;

    @BeforeEach
    void setUp(){
        parkhaus = new Parkhaus();
        automat = new Bezahlautomat();
        schranke = parkhaus.getEinfahrtSchranken()[0];
    }
    @Test
    @DisplayName("Unterschiedliche Tickets haben unterschiedliche IDs")
    void ticketErstellenTest() {
        TicketIF ticket1 = parkhaus.einfahrt(schranke);
        TicketIF ticket2 = parkhaus.einfahrt(schranke);
        assertNotEquals(ticket1.getID(), ticket2.getID());
    }

    @Test
    @DisplayName("Automat erkennt bezahlte Tickets")
    void ticketEntwertenTest() {
        TicketIF ticket = parkhaus.einfahrt(schranke);
        automat.einzahlen(1000);
        automat.bezahlen(ticket);

        assertTrue(ticket.istBezahlt());
    }

    @Test
    @DisplayName("Die Anzahl der freien Plätze kann gesetzt und abgerufen werden")
    void setAnzahlPlaetzeTest() {
        parkhaus.setAnzahlPlaetze(1500);
        assertEquals(1500, parkhaus.getAnzahlFreiePlaetze());
    }


    /**@RepeatedTest(10)
    @DisplayName("Anzahl freier Plätze wird bei Einfahrt reduziert")
    void reduzierungFreierPlaetzeTest() {
        int parkhausKapazitaet = rd.nextInt(100, 10000);
        parkhaus.setAnzahlPlaetze(parkhausKapazitaet);
        int einfahrten = rd.nextInt(1, 100);
        for (int i = einfahrten; 0 < i; i--){
            parkhaus.einfahrt(schranke);
        }
        assertEquals(parkhausKapazitaet - einfahrten, parkhaus.getAnzahlFreiePlaetze());
    }*/
}
