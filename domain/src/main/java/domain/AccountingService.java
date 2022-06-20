package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountingService {
    List<BusinessTravel> businessTravels = new ArrayList<>();

    public void accept(BusinessTravel businessTravel, LocalDateTime now) {
        for (BusinessTravel b : businessTravels) {
            if (twoBusinessTravelsOverlap(b, businessTravel)) {
                throw new IllegalStateException();
            }
        }
        if(isClosedForCommits(now)) {
            throw new IllegalArgumentException("Not possible to commit between 23.6. and 24.6.");
        }
        LocalDateTime deadLine = LocalDateTime.now().withMonth(1).withDayOfMonth(10).plusYears(1);
        businessTravels.add(businessTravel);

    }

    private boolean twoBusinessTravelsOverlap(BusinessTravel businessTravel1, BusinessTravel businessTravel2) {
        LocalDateTime startA = businessTravel1.getStart();
        LocalDateTime endA = businessTravel1.getEnd();
        LocalDateTime startB = businessTravel2.getStart();
        LocalDateTime endB = businessTravel2.getEnd();

        return startA.isBefore(endB) && endA.isAfter(startB);
    }

    private boolean isClosedForCommits(LocalDateTime now) {
        LocalDateTime june_23 = LocalDateTime.of(2022, 6, 23, 0, 0);
        LocalDateTime june_24 = LocalDateTime.of(2022, 6, 24, 23, 59);
        return now.isAfter(june_23) && now.isBefore(june_24);
    }

}
