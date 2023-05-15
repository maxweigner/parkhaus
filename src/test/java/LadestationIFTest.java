import models.Ticket;
import models.TicketIF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LadestationIFTest {
    private LadestationIF ladestation;
    private TicketIF ticket;

    @BeforeEach
    void setUp(){
        this.ladestation = new Ladestation("Green Energy");
        this.ticket = new Ticket(1, 1);
    }

    @Test
    @DisplayName("Ladevorgang kann gestartet werden")
    void startLadenTest() {
        boolean start = ladestation.startLaden(ticket);
        assertEquals(true, start);
    }

    @Test
    @DisplayName("Ladevorgang kann gestoppt werden")
    void stopLadenTest() {
        boolean stop = ladestation.stopLaden(ticket);
        assertEquals(false, stop);

    }

    @Test
    @DisplayName("Belegte Ladestation wird erkannt")
    void belegteStationTest() {
        boolean start = ladestation.startLaden(ticket);
        assertEquals(false, ladestation.startLaden(ticket));
    }
}