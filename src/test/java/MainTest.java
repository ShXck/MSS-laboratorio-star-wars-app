import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void div() {
        assertEquals(10, Main.div(20, 2));
    }

    @Test
    void div_by_zero() {
        assertThrows(ArithmeticException.class, ()->{Main.div(20, 0);});
    }
}