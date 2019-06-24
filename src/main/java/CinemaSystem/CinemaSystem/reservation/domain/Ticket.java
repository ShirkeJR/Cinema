package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ticket {

    private String type;
    private int count;
}
