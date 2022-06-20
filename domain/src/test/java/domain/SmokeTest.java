package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SmokeTest {
    private AccountingService accountingService;

    @BeforeEach
    public void setup() {
        accountingService = new AccountingService();
    }
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

    @Test
    void travel_end_date_must_not_be_the_same_as_travel_start_date() {
        LocalDateTime start = LocalDateTime.now().plusDays(2L);
        LocalDateTime end = start;
        String destination = "Sardinien";
        String reason = "eduCamp2022";
        assertThrows(IllegalArgumentException.class, () -> {
            new BusinessTravel(start, end, destination, reason);
        });
    }

    @Test
    public void accounting_accepts_only_one_simuoltaneous_travel() {
        LocalDateTime start1 = LocalDateTime.now().plusDays(2L);
        LocalDateTime end1 = LocalDateTime.now().plusDays(4);
        LocalDateTime start2 = LocalDateTime.now().plusDays(1L);
        LocalDateTime end2 = LocalDateTime.now().plusDays(3);
        String destination = "Sardinien";
        String reason = "eduCamp2022";

        BusinessTravel businessTravel1 = new BusinessTravel(start1, end1, destination, reason);
        BusinessTravel businessTravel2 = new BusinessTravel(start2, end2, destination, reason);

        AccountingService accountingService = new AccountingService();
        accountingService.accept(businessTravel1);
        assertThrows(IllegalStateException.class, () -> {
            accountingService.accept(businessTravel2);
        });
    }

    @Test
    public void accounting_accepts_two_travels() {
        LocalDateTime start1 = LocalDateTime.now().plusDays(2L);
        LocalDateTime end1 = LocalDateTime.now().plusDays(4);
        LocalDateTime start2 = LocalDateTime.now().plusDays(5);
        LocalDateTime end2 = LocalDateTime.now().plusDays(6);
        String destination = "Sardinien";
        String reason = "eduCamp2022";

        BusinessTravel businessTravel1 = new BusinessTravel(start1, end1, destination, reason);
        BusinessTravel businessTravel2 = new BusinessTravel(start2, end2, destination, reason);

        accountingService.accept(businessTravel1);
        accountingService.accept(businessTravel2);
    }

    @Test
    public void travel_can_be_inserted_only_after_10_January_of_the_following_year() {
        LocalDateTime start = LocalDateTime.now().withMonth(2).plusYears(1);
        LocalDateTime end = start.plusDays(2);
        String destination = "Sardinien";
        String reason = "eduCamp2022";
        BusinessTravel businessTravel = new BusinessTravel(start, end, destination, reason);
        assertThrows(IllegalArgumentException.class, () -> {
            accountingService.accept(businessTravel);
        });
    }

}
