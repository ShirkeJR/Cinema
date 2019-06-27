package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  private String id;
  private String title;
  private String description;

  public static Movie of(String id, CreateMovieCommand cmd) {
    return new Movie(id, cmd.title, cmd.description);
  }
}
