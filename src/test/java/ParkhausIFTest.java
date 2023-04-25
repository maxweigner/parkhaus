import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import services.*;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    private ParkhausIF parkhaus;
    private BezahlautomatIF automat;
    private final Random rd = new Random();
    SchrankeIF schranke;

    @BeforeEach
    void setUp(){
        parkhaus = new Parkhaus();
        int parkhausKapazitaet = rd.nextInt(899) + 100; // range(100, 999)
        parkhaus.setAnzahlPlaetze(parkhausKapazitaet);
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


    @RepeatedTest(10)
    @DisplayName("Anzahl freier Plätze wird bei Einfahrt reduziert")
    void reduzierungFreierPlaetzeTest() {
        int einfahrten = rd.nextInt(100);
        int kapazitaet = parkhaus.getAnzahlFreiePlaetze();
        for (int i = 0; i < einfahrten; i++){
            parkhaus.einfahrt(schranke);
        }
        assertEquals(kapazitaet - einfahrten, parkhaus.getAnzahlFreiePlaetze());
    }

    @Test
    @DisplayName("Tickets werden in einer Liste abgespeichert und bleiben erhalten")
    void erstellungTicketListeTest(){
        int einfahrten = rd.nextInt(100);
        for (int i = 0; i < einfahrten; i++){
            parkhaus.einfahrt(schranke);
        }
        List<TicketIF> ticketListe = parkhaus.getTicketListe();
        assertEquals(einfahrten, ticketListe.size());
    }
}
