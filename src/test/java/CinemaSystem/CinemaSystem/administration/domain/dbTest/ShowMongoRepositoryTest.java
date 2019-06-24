package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHall;
import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.ShowFactory;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShowMongoRepositoryTest {

  @Autowired private ShowRepository showRepository;
  @Autowired private ShowFactory showFactory;

  private final String id1 = UUID.randomUUID().toString();
  private final String id2 = UUID.randomUUID().toString();
  private Movie movie =
      Movie.builder().id(UUID.randomUUID().toString()).title("Raki").description("Rak").build();
  private CinemaHall cinemaHall = new CinemaHall();
  private Map<String, BigDecimal> tickets = Map.of("extra", new BigDecimal(30));

  @Test
  void shouldGetShowFromMongo() {
    var show = showFactory.create(id1, cinemaHall, movie, Instant.now(), tickets);
    showRepository.put(show);

    var actualShow = showRepository.get(show.getId());

    assertThat(actualShow.getId()).isEqualTo(show.getId());
  }

  @Test
  void shouldGetTwoShowsFromMongo() {
    var show1 = showFactory.create(id1, cinemaHall, movie, Instant.now().plus(1, ChronoUnit.DAYS), tickets);
    var show2 = showFactory.create(id2, cinemaHall, movie, Instant.now(), tickets);
    showRepository.put(show1);
    showRepository.put(show2);

    var actualShows = showRepository.getAll();

    assertThat(actualShows).isNotNull();
    assertThat(actualShows.size()).isEqualTo(3);
  }
}
