package domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AccountingService {
    List<BusinessTravel> businessTravels = new ArrayList<>();

    public Report generateReport() {
        LocalDate startDate = businessTravels.get(0).getStart().toLocalDate();
        LocalDate endDate = businessTravels.get(0).getEnd().toLocalDate();
        if(startDate.equals(endDate)) {
            Duration duration = Duration.between(businessTravels.get(0).getStart(), businessTravels.get(0).getEnd());
            return new Report(computePrice(duration));
        }
        int numberOfDays = (int)ChronoUnit.DAYS.between(startDate, endDate);
        Duration durationFirstDay = Duration.between(businessTravels.get(0).getStart(), businessTravels.get(0).getStart().withHour(23).withMinute(59).withSecond(59));
        Duration durationLastDay = Duration.between(businessTravels.get(0).getEnd().withHour(0).withMinute(0).withSecond(0), businessTravels.get(0).getEnd());
        return new Report(computePrice(durationFirstDay) + computePrice(durationLastDay) + (numberOfDays-1)*24);
    }

    private int computePrice(Duration duration) {
        int hours = (int)(duration.getSeconds()/3600);
        if(hours >= 12) {
            return 12;
        }
        else if(hours >= 8) {
            return 6;
        }
        else {
            return 0;
        }
    }

    public void accept(BusinessTravel businessTravel, LocalDateTime now) {
        if (businessTravels.stream().anyMatch(b -> b.doesOverlap(businessTravel))) {
            throw new IllegalStateException("New business trip overlaps with existing business trip");
        }
        validateOverDeadline(businessTravel, now);
        validateClosedForCommits(now);
        businessTravels.add(businessTravel);
    }

    private void validateOverDeadline(BusinessTravel businessTravel, LocalDateTime now) {
        LocalDateTime deadLine = businessTravel.getEnd()
                .plusYears(1)
                .withDayOfMonth(10)
                .withMonth(1)
                .withHour(23)
                .withMinute(59);
        if (now.isAfter(deadLine)) {
            throw new IllegalArgumentException("Travel expenses were committed too late");
        }
    }

    private void validateClosedForCommits(LocalDateTime now) {
        LocalDateTime june_23 = LocalDateTime.of(2022, 6, 23, 0, 0);
        LocalDateTime june_24 = LocalDateTime.of(2022, 6, 24, 23, 59);
        if (now.isAfter(june_23) && now.isBefore(june_24)) {
            throw new IllegalArgumentException("Not possible to commit between 23.6. and 24.6.");
        }
    }
}
