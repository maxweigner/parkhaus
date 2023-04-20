import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.Einnahmen;
import services.EinnahmenIF;

import static org.junit.jupiter.api.Assertions.*;

class EinnahmenIFTest {

    EinnahmenIF in;

    @Test
    @DisplayName("services.Einnahmen können ein- und ausgegeben werden")
    void addIncome_equals() {
        in = new Einnahmen();

        in.addIncome(4.5f);
        in.addIncome(3.86f);

        assertEquals(4.5f+3.86f, in.totalIncome());
    }
    @Test
    @DisplayName("services.Einnahmen werden nicht verfälscht")
    void addIncome_notEquals() {
        in = new Einnahmen();

        in.addIncome(4.5f);
        in.addIncome(3.86f);

        assertNotEquals(4.5f+3.85f, in.totalIncome());
    }
}