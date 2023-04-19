import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BezahlautomatIFTest {
    ParkhausIF parkhaus;
    BezahlautomatIF automat;


    @BeforeEach
    void setUp(){
        parkhaus = new Parkhaus();
        automat = new Bezahlautomat();
    }

    @DisplayName("Nach Bezahlvorgang wird Endstempel zur Berechtigung der Ausfahrt eingetragen")
    @Test
    void ticketBezahlenTest() {
        TicketIF ticket1 = parkhaus.einfahrt();
        automat.bezahlen(ticket1);
        assertNotEquals(null, ticket1.getEnde());
    }
}