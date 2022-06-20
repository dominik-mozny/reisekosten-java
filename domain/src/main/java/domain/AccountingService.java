package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountingService {
    List<BusinessTravel> businessTravels = new ArrayList<>();

    public void accept(BusinessTravel businessTravel, LocalDateTime now) {
        businessTravels.stream().forEach(b -> validateIfTwoBusinessTravelsOverlap(b, businessTravel));
        validateOverDeadline(businessTravel, now);
        validateClosedForCommits(now);
        businessTravels.add(businessTravel);
    }

    private void validateOverDeadline(BusinessTravel businessTravel, LocalDateTime now) {
        LocalDateTime deadLine =  businessTravel.getEnd()
                .plusYears(1)
                .withDayOfMonth(10)
                .withMonth(1)
                .withHour(23)
                .withMinute(59);
        if (now.isAfter(deadLine)) {
            throw new IllegalArgumentException("Travel expenses were committed too late");
        }
    }

    private void validateIfTwoBusinessTravelsOverlap(BusinessTravel businessTravel1, BusinessTravel businessTravel2) {
        LocalDateTime startA = businessTravel1.getStart();
        LocalDateTime endA = businessTravel1.getEnd();
        LocalDateTime startB = businessTravel2.getStart();
        LocalDateTime endB = businessTravel2.getEnd();

        if(startA.isBefore(endB) && endA.isAfter(startB)) {
            throw new IllegalStateException("New business trip overlaps with existing business trip");
        }
    }

    private void validateClosedForCommits(LocalDateTime now) {
        LocalDateTime june_23 = LocalDateTime.of(2022, 6, 23, 0, 0);
        LocalDateTime june_24 = LocalDateTime.of(2022, 6, 24, 23, 59);
        if(now.isAfter(june_23) && now.isBefore(june_24)) {
            throw new IllegalArgumentException("Not possible to commit between 23.6. and 24.6.");
        }
    }
}
