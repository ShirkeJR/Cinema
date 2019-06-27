package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CinemaMongoRepositoryTest {

  @Autowired private CinemaRepository cinemaRepository;
  @Autowired private MongoTemplate mongoTemplate;

  @BeforeEach
  void setUp() {
    mongoTemplate.dropCollection("cinemas");
  }


  @Test
  void shouldGetCinemaFromMongo() {
    var cinema = createCinema(UUID.randomUUID().toString(),"Lublim", "Plaza");

    cinemaRepository.put(cinema);

    var actualCinema = cinemaRepository.get(cinema.getId());

    assertThat(actualCinema.getId()).isEqualTo(cinema.getId());
    assertThat(actualCinema.getCity()).isEqualTo(cinema.getCity());
    assertThat(actualCinema.getName()).isEqualTo(cinema.getName());
  }

  @Test
  void shouldGetTwoCinemasFromMongo() {
    var cinema1 = createCinema(UUID.randomUUID().toString(),"Poznań", "Plao");
    var cinema2 = createCinema(UUID.randomUUID().toString(),"Szczecin", "KINO");

    cinemaRepository.put(cinema1);
    cinemaRepository.put(cinema2);

    var actualCinemas = cinemaRepository.getAll();

    assertThat(actualCinemas).isNotNull();
    assertThat(actualCinemas.size()).isEqualTo(2);
  }

  private Cinema createCinema(String id, String city, String name) {
    var cmd = prepareCreateCinemaCommand(city, name);
    return Cinema.of(id, cmd);
  }

  private CreateCinemaCommand prepareCreateCinemaCommand(String city, String name) {
    var cmd = new CreateCinemaCommand();
    cmd.city = city;
    cmd.name = name;
    return cmd;
  }
}
