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
    var cmd = prepareCreateMovieCommand();
    var cinema = Movie.builder().id(id).title(cmd.title).description(cmd.description).build();

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
