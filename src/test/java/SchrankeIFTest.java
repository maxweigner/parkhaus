import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchrankeIFTest {

    Schranke schranke;

    @BeforeEach
    void setUp() {
        schranke = new Schranke();
    }

    @Test
    @DisplayName("Schranke kann für Einfahrt genutzt werden")
    void setSchranke_einfahrt() {
        schranke.setSchranke("einfahrt");
        assertEquals("einfahrt", schranke.getSchranke());
    }

    @Test
    @DisplayName("Schranke kann für Ausfahrt genutzt werden")
    void setSchranke_ausfahrt() {
        schranke.setSchranke("ausfahrt");
        assertEquals("ausfahrt",schranke.getSchranke());
    }

    @Test
    @DisplayName("Schranke kann nur für Einfahrt oder Ausfahrt genutzt werden")
    void setSchranke_random_exception() {
        assertThrows(IllegalArgumentException.class, () -> {schranke.setSchranke("hallo");});
        assertThrows(IllegalArgumentException.class, () -> {schranke.setSchranke("kevin");});
        assertThrows(IllegalArgumentException.class, () -> {schranke.setSchranke("beides");});
    }


    @Test
    @DisplayName("Schranke öffnet wenn sie geschlossen ist")
    void openSchranke_geschlossen_öffnet() {
        assertTrue(schranke.open());
    }

    @Test
    @DisplayName("Schranke öffnet nicht wenn sie offen ist")
    void openSchranke_offen_öffnetNicht() {
        schranke.open();
        assertFalse(schranke.open());
    }

    @Test
    @DisplayName("Schranke schließt nicht wenn sie geschlossen ist")
    void closeSchranke_geschlossen_schließtNicht() {
        assertFalse(schranke.close());
    }

    @Test
    @DisplayName("Schranke schließt wenn sie offen ist")
    void closeSchranke_offen_schließt() {
        schranke.open();
        assertTrue(schranke.close());
    }
}