package CinemaSystem.CinemaSystem.administration.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Movie {

  private UUID id;
  private String title;
  private String description;
}
