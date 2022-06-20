package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountingService {
    List<BusinessTravel> businessTravels = new ArrayList<>();

    public void accept(BusinessTravel businessTravel) {
        businessTravels.add(businessTravel);
    }

    private boolean twoBusinessTravelsOverlap(BusinessTravel businessTravel1, BusinessTravel businessTravel2) {
        LocalDateTime startA = businessTravel1.getStart();
        LocalDateTime endA = businessTravel1.getEnd();
        LocalDateTime startB = businessTravel2.getStart();
        LocalDateTime endB = businessTravel2.getEnd();

        return startA.isb
    }

}
