package domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class BusinessTravel {
    private LocalDateTime start;
    private LocalDateTime end;
    private String destination;
    private String reason;

    public BusinessTravel(LocalDateTime start, LocalDateTime end, String destination, String reason) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(destination);
        Objects.requireNonNull(reason);
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Travel end date must occur after travel start date");
        }
        this.start = start;
        this.end = end;
        this.destination = destination;
        this.reason = reason;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getDestination() {
        return destination;
    }

    public String getReason() {
        return reason;
    }
}
