package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaTest {

  private final String id = UUID.randomUUID().toString();
  private final String city = "Lublin";
  private final String name = "Cinema";

  @Test
  void createsNewCinema() {
    var cmd = prepareCreateCinemaCommand(city, name);
    var cinema = Cinema.of(id, cmd);

    assertThat(cinema.getId()).isEqualTo(id);
    assertThat(cinema.getName()).isEqualTo(name);
    assertThat(cinema.getCity()).isEqualTo(city);
  }

  private CreateCinemaCommand prepareCreateCinemaCommand(String city, String name) {
    var cmd = new CreateCinemaCommand();
    cmd.city = city;
    cmd.name = name;
    return cmd;
  }
}
