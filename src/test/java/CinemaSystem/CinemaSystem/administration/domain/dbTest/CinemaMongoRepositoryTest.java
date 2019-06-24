package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CinemaMongoRepositoryTest {

  @Autowired private CinemaRepository cinemaRepository;

  private final String id1 = UUID.randomUUID().toString();
  private final String id2 = UUID.randomUUID().toString();
  private final String city = "Lublin";
  private final String name = "Cinema";

  @Test
  void shouldGetCinemaFromMongo() {
    var cinema = Cinema.builder()
            .id(id1)
            .city(city)
            .name(name)
            .build();
    cinemaRepository.put(cinema);

    var actualCinema = cinemaRepository.get(cinema.getId());

    assertThat(actualCinema.getId()).isEqualTo(cinema.getId());
    assertThat(actualCinema.getCity()).isEqualTo(cinema.getCity());
    assertThat(actualCinema.getName()).isEqualTo(cinema.getName());
  }

    @Test
    void shouldGetTwoCinemasFromMongo() {
        var cinema1 = Cinema.builder()
                .id(id1)
                .city(city)
                .name(name)
                .build();
        var cinema2 = Cinema.builder()
                .id(id2)
                .city(city)
                .name(name)
                .build();
        cinemaRepository.put(cinema1);
        cinemaRepository.put(cinema2);

        var actualCinemas = cinemaRepository.getAll();

        assertThat(actualCinemas).isNotNull();
        assertThat(actualCinemas.size()).isEqualTo(2);
    }
}
