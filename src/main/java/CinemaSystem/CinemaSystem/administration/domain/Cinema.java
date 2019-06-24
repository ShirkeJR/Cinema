package CinemaSystem.CinemaSystem.administration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

  private String id;
  private String name;
  private String city;
}
