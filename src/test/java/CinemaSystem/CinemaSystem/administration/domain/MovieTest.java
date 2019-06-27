package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MovieTest {

  private final String id = UUID.randomUUID().toString();
  private final String title = "Szczęki";
  private final String desc = "Se pływa rekin";

  @Test
  void createsMovie() {
    var cmd = prepareCreateMovieCommand(title, desc);
    var movie = Movie.of(id, cmd);

    assertThat(movie.getId()).isEqualTo(id);
    assertThat(movie.getTitle()).isEqualTo(title);
    assertThat(movie.getDescription()).isEqualTo(desc);
  }

  private CreateMovieCommand prepareCreateMovieCommand(String title, String desc) {
    var cmd = new CreateMovieCommand();
    cmd.title = title;
    cmd.description = desc;
    return cmd;
  }
}
