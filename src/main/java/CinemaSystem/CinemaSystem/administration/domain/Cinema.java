package CinemaSystem.CinemaSystem.administration.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cinema {

  private String id;
  private String name;
  private String city;
}
