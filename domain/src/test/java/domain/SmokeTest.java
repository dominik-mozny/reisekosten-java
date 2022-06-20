package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmokeTest {
    @Test
    void addition() {
        assertEquals(1, 1);
    }

    @Test
    void creation_of_business_travel_with_valid_parameters() {
        LocalDateTime start = LocalDateTime.now().minusDays(2L);
        LocalDateTime end = LocalDateTime.now().plusDays(5L);
        String destination = "Sardinien";
        String reason = "eduCamp2022";

        new BusinessTravel(start, end, destination, reason);
    }
}
