package CinemaSystem.CinemaSystem.administration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

  private String id;
  private String title;
  private String description;
}
