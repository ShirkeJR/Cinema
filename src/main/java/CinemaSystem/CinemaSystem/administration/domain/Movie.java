package CinemaSystem.CinemaSystem.administration.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Movie {

  private String id;
  private String title;
  private String description;
}
