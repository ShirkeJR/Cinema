package CinemaSystem.CinemaSystem.administration;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;

public class MovieTest {

  private final UUID id = UUID.randomUUID();
  private final String title = "Szczęki";
  private final String desc = "Se pływa rekin";

  @Test
  void createsMovie() {
    var cmd = prepareCreateMovieCommand();
    var cinema = Movie.builder()
            .id(id)
            .title(cmd.title)
            .description(cmd.description)
            .build();

    assertThat(cinema.getId()).isEqualTo(id);
    assertThat(cinema.getTitle()).isEqualTo(title);
    assertThat(cinema.getDescription()).isEqualTo(desc);
  }

  private CreateMovieCommand prepareCreateMovieCommand() {
    var cmd = new CreateMovieCommand();
    cmd.title = title;
    cmd.description = desc;
    return cmd;
  }
}
