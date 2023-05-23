import models.Ticket;
import models.TicketIF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import services.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @DisplayName("indivudelles Parkhaus kann erstellt werden")
    void erstellungParkhaus(){
        int kapazitaet = rd.nextInt(1000) + 100; // range()
        int einfahrten = rd.nextInt(9) + 1; // range(1,10)
        int ausfahrten = rd.nextInt(9) + 1;
        ParkhausIF parkhaus = new Parkhaus(kapazitaet, einfahrten, ausfahrten);
        assertEquals(kapazitaet, parkhaus.getAnzahlFreiePlaetze());
        assertEquals(einfahrten, parkhaus.getEinfahrtSchranken().length);
        assertEquals(ausfahrten, parkhaus.getAusfahrtSchranken().length);
    }

    @Test
    @DisplayName("Bezahlte Tickets werden richtig ermittelt")
    void bezahlteTicketsErmitteln(){
        SchrankeIF schranke = parkhaus.getAusfahrtSchranken()[0];
        int einfahrten = rd.nextInt(9) + 1; // range(1,10)
        for (int i = 0; i < einfahrten; i++){
            parkhaus.einfahrt(schranke);
        }
        assertEquals(einfahrten, parkhaus.getUnbezahlteTickets().length);
        parkhaus.getAutomat().bezahlen(parkhaus.getTicket(1), LocalDateTime.now());
        assertEquals(1, parkhaus.getBezahlteTickets().length);
        assertEquals(einfahrten - 1, parkhaus.getUnbezahlteTickets().length);
    }

    @Test
    @DisplayName("Kulanzfrist von 15 Minuten besteht zwischen Zahlung und Ausfahrt")
    void kulanzfristBeiAusfahrt(){
        TicketIF ticket = parkhaus.einfahrt(schranke);

        LocalDateTime jetzt = LocalDateTime.now();
        LocalDateTime zuLangsam = jetzt.plusMinutes(20);
        parkhaus.getAutomat().bezahlen(ticket, jetzt);
        ticket.setAusfahrtsZeit(zuLangsam);
        assertEquals(false, parkhaus.ausfahrt(ticket, parkhaus.getAusfahrtSchranken()[0]));
    }

    @Test
    @DisplayName("Belegte Ladestationen werden richtig ermittelt")
    void belegteLadestationenTest(){
        TicketIF t1 = new Ticket(1, 1);
        TicketIF t2 = new Ticket(2, 1);
        //parkhaus.startLaden(t1);
        //parkhaus.startLaden(t2);
        assertEquals(2, parkhaus.getLadendeTickets().length);
        //parkhaus.stopLaden(t1);
        assertEquals(1, parkhaus.getLadendeTickets().length);
    }

    @Test
    @DisplayName("Tickets werden richtig nach ladend und nicht ladened gefiltert")
    void nichtLadendeTicketFilterungTest(){
        TicketIF t1 = parkhaus.einfahrt(schranke);
        TicketIF t2 = parkhaus.einfahrt(schranke);
        assertEquals(0, parkhaus.getLadendeTickets().length);
        assertEquals(2, parkhaus.getNichtLadendeTickets().length);
        parkhaus.startLaden(t1, LocalDateTime.now());
        assertEquals(1, parkhaus.getLadendeTickets().length);
        assertEquals(1, parkhaus.getNichtLadendeTickets().length);
        parkhaus.stopLaden(t1, LocalDateTime.now().plusHours(2), 2);
        t1.setStartLadeZeit(null);
        assertEquals(0, parkhaus.getLadendeTickets().length);
        assertEquals(2, parkhaus.getNichtLadendeTickets().length);
    }
}
