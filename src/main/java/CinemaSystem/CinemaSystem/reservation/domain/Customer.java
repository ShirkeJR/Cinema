package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  private String email;
  private String phoneNumer;
  private String firstName;
  private String lastName;
}
