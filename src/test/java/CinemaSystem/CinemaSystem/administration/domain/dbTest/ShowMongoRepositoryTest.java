package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShowMongoRepositoryTest {

  @Autowired private ShowRepository showRepository;
  @Autowired private ShowFactory showFactory;

  private Map<String, BigDecimal> tickets = Map.of("extra", new BigDecimal(30));
  private final String cinemaId = UUID.randomUUID().toString();
  private final String movieId = UUID.randomUUID().toString();
  private Cinema cinema = createCinema(cinemaId, "Lubin", "Plaza");
  private Movie movie = createMovie(movieId, "Szczęki", "rekiny");
  private CinemaHall cinemaHall = CinemaHall.of(10, 10);

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void getsShowFromMongo() {
    var show =
        createShow(
            UUID.randomUUID().toString(),
            cinema,
            movie,
            cinemaHall,
                LocalDateTime.now().plus(2, ChronoUnit.DAYS),
            tickets);

    showRepository.put(show);

    var actualShow = showRepository.get(show.getId());

    assertThat(actualShow.getId()).isEqualTo(show.getId());
    assertThat(actualShow.getTicketPrices()).isEqualTo(show.getTicketPrices());
    assertThat(actualShow.getMovie().getId()).isEqualTo(show.getMovie().getId());
    assertThat(actualShow.getTime().getHour()).isEqualTo(show.getTime().getHour());
    assertThat(actualShow.getReservations()).isEqualTo(show.getReservations());
    assertThat(actualShow.getCinemaHall().getCinemaHallSeats().size()).isEqualTo(show.getCinemaHall().getCinemaHallSeats().size());
    assertThat(actualShow.getCinema().getId()).isEqualTo(show.getCinema().getId());
  }

  @Test
  void getsTwoShowsFromMongo() {
    var show1 =
        createShow(
            UUID.randomUUID().toString(),
            cinema,
            movie,
            cinemaHall,
                LocalDateTime.now().plus(2, ChronoUnit.DAYS),
            tickets);
    var show2 =
        createShow(
            UUID.randomUUID().toString(),
            cinema,
            movie,
            cinemaHall,
                LocalDateTime.now().plus(2, ChronoUnit.DAYS),
            tickets);
    ;
    showRepository.put(show1);
    showRepository.put(show2);

    var actualShows = showRepository.getAll();

    assertThat(actualShows).isNotNull();
    assertThat(actualShows.size()).isEqualTo(3);
  }

  private Show createShow(
      String id,
      Cinema cinema,
      Movie movie,
      CinemaHall cinemaHall,
      LocalDateTime time,
      Map<String, BigDecimal> tickets) {
    var cmd = prepareCreateShowCommand(cinema.getId(), movie.getId(), time, tickets);
    return showFactory.create(id, cinema, cinemaHall, movie, cmd);
  }

  private CreateShowCommand prepareCreateShowCommand(
          String cinemaId, String movieId, LocalDateTime time, Map<String, BigDecimal> tickets) {
    var cmd = new CreateShowCommand();
    cmd.cinemaId = cinemaId;
    cmd.movieId = movieId;
    cmd.time = time;
    cmd.tickets = tickets;
    return cmd;
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

  private Movie createMovie(String id, String title, String desc) {
    var cmd = prepareCreateMovieCommand(title, desc);
    return Movie.of(id, cmd);
  }

  private CreateMovieCommand prepareCreateMovieCommand(String title, String desc) {
    var cmd = new CreateMovieCommand();
    cmd.title = title;
    cmd.description = desc;
    return cmd;
  }
}
