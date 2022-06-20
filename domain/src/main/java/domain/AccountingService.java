package domain;

import java.util.ArrayList;
import java.util.List;

public class AccountingService {
    List<BusinessTravel> businessTravels = new ArrayList<>();

    public void accept(BusinessTravel businessTravel) {
        if (!businessTravels.isEmpty()) {
            throw new IllegalStateException();
        }
    }

}
