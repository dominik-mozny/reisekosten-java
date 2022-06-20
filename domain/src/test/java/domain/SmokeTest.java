package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void travel_end_date_must_occur_after_travel_start_date() {
        LocalDateTime start = LocalDateTime.now().plusDays(2L);
        LocalDateTime end = LocalDateTime.now().minusDays(5L);
        String destination = "Sardinien";
        String reason = "eduCamp2022";
        assertThrows(IllegalArgumentException.class, () -> {
            new BusinessTravel(start, end, destination, reason);
        });
    }
}
