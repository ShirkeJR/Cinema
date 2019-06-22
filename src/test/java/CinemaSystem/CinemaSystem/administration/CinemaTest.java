package CinemaSystem.CinemaSystem.administration;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaTest {

  private final UUID id = UUID.randomUUID();
  private final String city = "Lublin";
  private final String name = "Cinema";

  @Test
  void createsCinema() {
    var cmd = prepareCreateCinemaCommand();
    var cinema = Cinema.builder()
            .id(id)
            .city(cmd.city)
            .name(cmd.name)
            .build();

    assertThat(cinema.getId()).isEqualTo(id);
    assertThat(cinema.getName()).isEqualTo(name);
    assertThat(cinema.getCity()).isEqualTo(city);
  }

  private CreateCinemaCommand prepareCreateCinemaCommand() {
    var cmd = new CreateCinemaCommand();
    cmd.city = city;
    cmd.name = name;
    return cmd;
  }
}
