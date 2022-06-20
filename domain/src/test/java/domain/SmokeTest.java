package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeTest {
    @Test
    void addition() {
        assertEquals(1, 1);
    }

    @Test
    void should_accept_businessTravel(){
        new BussinessTravel();
    }
}
