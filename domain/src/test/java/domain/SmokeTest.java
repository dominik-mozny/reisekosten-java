package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmokeTest {
    @Test
    void addition() {
        assertEquals(1, 1);
    }

    @Test
    void should_accept_businessTravel(){
        new BusinessTravel(LocalDateTime.now(), LocalDateTime.now(), "dest", "reason");
    }

    @Test
    void creation_of_business_travel_fails_because_of_the_parameter_is_missing() {
        LocalDateTime start = null;
        LocalDateTime end = null;
        String destination = null;
        String reason = null;

        assertThrows(NullPointerException.class, () -> {
            new BusinessTravel(start, end, destination, reason);
        });
    }
}
