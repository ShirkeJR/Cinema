package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatAndTicketCountException;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ShowTest {

  private final String id = UUID.randomUUID().toString();
  private final Instant time = Instant.now().plus(2L, ChronoUnit.DAYS);
  private final Map<String, BigDecimal> tickets =
      Map.of(
          "normal", new BigDecimal(20),
          "extra", new BigDecimal(30));

  private final String cinemaId = UUID.randomUUID().toString();
  private final String cinemaCity = "Lublin";
  private final String cinemaName = "Cinema";
  private final Cinema cinema =
      Cinema.builder().id(cinemaId).city(cinemaCity).name(cinemaName).build();

  private final int CINEMA_HALL_ROWS = 20;
  private final int CINEMA_HALL_COLUMNS = 30;
  private final CinemaHall cinemaHall =
      new CinemaHall(cinema, CINEMA_HALL_ROWS, CINEMA_HALL_COLUMNS);

  private final String movieId = UUID.randomUUID().toString();
  private final String movieTitle = "Szczęki";
  private final String movieDesc = "Se pływa rekin";
  private final Movie movie =
      Movie.builder().id(movieId).title(movieTitle).description(movieDesc).build();

  @Mock
  private ShowReservation showReservation;
  @Mock
  private ShowTicketCalculator showTicketCalculator;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createsShow() {
    var cmd = prepareCreateShowCommand();
    var show = new Show(id, movie, cinemaHall, cmd.time, cmd.tickets, showTicketCalculator);

    assertThat(show.getId()).isEqualTo(id);
    assertThat(show.getCinemaHall()).isEqualTo(cinemaHall);
    assertThat(show.getMovie().getId()).isEqualTo(movieId);
    assertThat(show.getTime()).isEqualTo(time);
    assertThat(show.getTicketPrices()).isEqualTo(tickets);
  }

  private CreateShowCommand prepareCreateShowCommand() {
    var cmd = new CreateShowCommand();
    cmd.cinemaId = cinemaId;
    cmd.movieId = movieId;
    cmd.time = time;
    cmd.tickets = tickets;
    return cmd;
  }

  @Test
  void reservesShow() {
    var show = createShow();
    var reservedSeat = new Seat(2, 3);
    when(showReservation.getReservedSeats()).thenReturn(Sets.newHashSet(reservedSeat));

    show.reserveReservation(showReservation);

    assertThat(show.getReservations().size()).isEqualTo(1);
    assertThat(show.getReservations().get(0)).isEqualTo(showReservation);
  }

  @Test
  void unReservesShow() {
    var show = createShow();
    var reservedSeat = new Seat(2, 3);
    when(showReservation.getReservedSeats()).thenReturn(Sets.newHashSet(reservedSeat));
    show.reserveReservation(showReservation);

    show.unReserveReservation(showReservation);

    assertThat(show.getReservations().isEmpty()).isTrue();
  }

  private Show createShow() {
    var cmd = prepareCreateShowCommand();
    return new Show(id, movie, cinemaHall, cmd.time, cmd.tickets, showTicketCalculator);
  }

  @Test
  void expectsInvalidSeatAndTicketCountExceptionWhenTicketsCountIsNotEqualToSeatsCount() {
    var seatsCount = 4;
    var reservedTickets = List.of(
            new Ticket("normal", 2),
            new Ticket("extra", 1));
    var show = createShow();

    Executable executable = () -> show.calculateTicketsPerSeats(reservedTickets, seatsCount);

    assertThrows(InvalidSeatAndTicketCountException.class, executable);
  }

}
