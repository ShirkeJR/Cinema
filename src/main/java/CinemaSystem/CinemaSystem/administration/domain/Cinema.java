package CinemaSystem.CinemaSystem.administration.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Cinema {

  private UUID id;
  private String name;
  private String city;
}
