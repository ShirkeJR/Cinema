package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatAndTicketCountException;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import com.google.common.collect.Sets;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShowTest {

  private final String showId = UUID.randomUUID().toString();
  private final String cinemaId = UUID.randomUUID().toString();
  private final String movieId = UUID.randomUUID().toString();

  private final LocalDateTime time = LocalDateTime.now().plus(2, ChronoUnit.DAYS);
  private final Map<String, BigDecimal> tickets =
      Map.of(
          "normal", new BigDecimal(20),
          "extra", new BigDecimal(30));
  @Mock private TicketCalculator ticketCalculator;
  @Mock private ShowReservation showReservation;
  @Mock private Movie movie;
  @Mock private Cinema cinema;
  @Mock private CinemaHall cinemaHall;
  private final ShowFactory showFactory = new ShowFactory(ticketCalculator);

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void createsShow() {
    var show = createShow();

    assertThat(show.getId()).isEqualTo(showId);
    assertThat(show.getTime()).isEqualTo(time);
    assertThat(show.getTicketPrices()).isEqualTo(tickets);
  }

  @Test
  void reservesOneSeatOnShow() {
    var show = createShow();
    var reservedSeat = Seat.of(2, 3);
    when(showReservation.getReservedSeats()).thenReturn(Set.of(reservedSeat));

    show.reserveReservation(showReservation);

    assertThat(show.getReservations().size()).isEqualTo(1);
    assertThat(show.getReservations().get(0)).isEqualTo(showReservation);
  }

  @Test
  void triesToReserveReservedShow() {
    var show = createShow();
    var reservedSeat = Seat.of(2, 3);
    when(showReservation.getReservedSeats()).thenReturn(Set.of(reservedSeat));

    show.reserveReservation(showReservation);

    assertThat(show.getReservations().size()).isEqualTo(1);
    assertThat(show.getReservations().get(0)).isEqualTo(showReservation);
  }

  @Test
  void cancelReservationOnShow() {
    var show = createShow();
    var reservedSeat = Seat.of(2, 3);
    when(showReservation.getReservedSeats()).thenReturn(Sets.newHashSet(reservedSeat));
    show.reserveReservation(showReservation);

    show.unReserveReservation(showReservation);

    assertThat(show.getReservations().isEmpty()).isTrue();
  }

  @Test
  void expectsInvalidSeatAndTicketCountExceptionWhenTicketsCountIsNotEqualToSeatsCount() {
    var seatsCount = 4;
    var reservedTickets = Set.of(Ticket.of("normal", 2), Ticket.of("extra", 1));
    var show = createShow();

    Executable executable = () -> show.calculateTicketsPerSeats(reservedTickets, seatsCount);

    assertThrows(InvalidSeatAndTicketCountException.class, executable);
  }

  private CreateShowCommand prepareCreateShowCommand() {
    var cmd = new CreateShowCommand();
    cmd.cinemaId = cinemaId;
    cmd.movieId = movieId;
    cmd.time = time;
    cmd.tickets = tickets;
    return cmd;
  }

  private Show createShow() {
    var cmd = prepareCreateShowCommand();
    return showFactory.create(showId, cinema, cinemaHall, movie, cmd);
  }
}
