package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Customer {

    private String email;
    private String phoneNumer;
    private String firstName;
    private String lastName;
}
