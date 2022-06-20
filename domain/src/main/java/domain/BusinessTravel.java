package domain;

import java.time.LocalDateTime;

public class BusinessTravel {
    private LocalDateTime start;
    private LocalDateTime end;
    private String destination;
    private String reason;
    public BusinessTravel(LocalDateTime start, LocalDateTime end, String destination, String reason){
        this.start = start;
        this.end = end;
        this.destination = destination;
        this.reason = reason;
    }

}
