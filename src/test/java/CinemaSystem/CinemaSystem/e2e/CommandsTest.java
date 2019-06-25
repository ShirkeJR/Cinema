package CinemaSystem.CinemaSystem.e2e;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.*;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.PayShowReservationCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommandsTest {

  @Autowired private CommandGateway commandGateway;
  @Autowired private MovieRepository movieRepository;
  @Autowired private CinemaRepository cinemaRepository;
  @Autowired private ShowRepository showRepository;
  @Autowired private ShowReservationRepository showReservationRepository;

  @Test
  public void createsCinema() {
    CreateCinemaCommand createCinemaCommand = createCinemaCommand();

    String id = commandGateway.execute(createCinemaCommand);

    Cinema cinema = cinemaRepository.get(id);
    assertThat(cinema).isNotNull();
    assertThat(cinema.getId()).isEqualTo(id);
    assertThat(cinema.getName()).isEqualTo(createCinemaCommand.name);
    assertThat(cinema.getCity()).isEqualTo(createCinemaCommand.city);
  }

  @Test
  public void createsMovie() {
    CreateMovieCommand createMovieCommand = createMovieCommand();

      String id = commandGateway.execute(createMovieCommand);

    Movie movie = movieRepository.get(id);
    assertThat(movie).isNotNull();
    assertThat(movie.getId()).isEqualTo(id);
    assertThat(movie.getTitle()).isEqualTo(createMovieCommand.title);
    assertThat(movie.getDescription()).isEqualTo(createMovieCommand.description);
  }

  @Test
  public void createsShow() {
    CreateShowCommand createShowCommand = createShowCommand();

    String id = commandGateway.execute(createShowCommand);

    Show show = showRepository.get(id);
    assertThat(show).isNotNull();
    assertThat(show.getId()).isEqualTo(id);
    assertThat(show.getReservations().isEmpty());
    assertThat(show.getMovie().getId()).isEqualTo(createShowCommand.movieId);
    assertThat(show.getTicketPrices()).isEqualTo(createShowCommand.tickets);
    assertThat(show.getTime()).isEqualTo(Date.from(createShowCommand.time));
  }

  @Test
  void createsShowReservation() {
    CreateShowReservationCommand createShowReservationCommand = createShowReservationCommand();
    ShowReservation createdShowReservation = commandGateway.execute(createShowReservationCommand);

    ShowReservation showReservation = showReservationRepository.get(createdShowReservation.getId());

    assertThat(showReservation).isNotNull();
    assertThat(showReservation.getId()).isEqualTo(createdShowReservation.getId());
    assertThat(showReservation.getCustomer()).isNotNull();
    assertThat(showReservation.getReservedSeats().size())
        .isEqualTo(createShowReservationCommand.reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(2);
    assertThat(showReservation.getShowId()).isEqualTo(createShowReservationCommand.showId);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(CONFIRMED);
  }

  @Test
  void createsPayedShowReservation() {
    CreatePayedShowReservationCommand createPayedShowReservationCommand =
        createPayedShowReservationCommand();
    ShowReservation createdShowReservation = commandGateway.execute(createPayedShowReservationCommand);

    ShowReservation showReservation = showReservationRepository.get(createdShowReservation.getId());

    assertThat(showReservation).isNotNull();
    assertThat(showReservation.getId()).isEqualTo(createdShowReservation.getId());
    assertThat(showReservation.getCustomer()).isNotNull();
    assertThat(showReservation.getReservedSeats().size())
        .isEqualTo(createPayedShowReservationCommand.reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(2);
    assertThat(showReservation.getShowId()).isEqualTo(createPayedShowReservationCommand.showId);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(PAYED);
  }

  @Test
  void cancelsShowReservation() {
    CreateShowReservationCommand createShowReservationCommand = createShowReservationCommand();
    ShowReservation createdShowReservation = commandGateway.execute(createShowReservationCommand);
    CancelShowReservationCommand cancelShowReservationCommand =
        cancelShowReservationCommand(createdShowReservation.getId());

    String id = commandGateway.execute(cancelShowReservationCommand);

    ShowReservation showReservation = showReservationRepository.get(id);
    assertThat(showReservation).isNotNull();
    assertThat(createdShowReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getReservedSeats().size())
        .isEqualTo(createShowReservationCommand.reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(2);
    assertThat(showReservation.getShowId()).isEqualTo(createShowReservationCommand.showId);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(CANCELED);
  }

  @Test
  void paysShowReservation() {
    CreateShowReservationCommand createShowReservationCommand = createShowReservationCommand();
    ShowReservation createdShowReservation = commandGateway.execute(createShowReservationCommand);
    PayShowReservationCommand payShowReservationCommand =
        payShowReservationCommand(createdShowReservation.getId());

    String id = commandGateway.execute(payShowReservationCommand);

    ShowReservation showReservation = showReservationRepository.get(id);
    assertThat(showReservation).isNotNull();
    assertThat(createdShowReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getReservedSeats().size())
        .isEqualTo(createShowReservationCommand.reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(2);
    assertThat(showReservation.getShowId()).isEqualTo(createShowReservationCommand.showId);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(PAYED);
  }

  private PayShowReservationCommand payShowReservationCommand(String reservationId) {
    PayShowReservationCommand payShowReservationCommand = new PayShowReservationCommand();
    payShowReservationCommand.reservationId = reservationId;
    return payShowReservationCommand;
  }

  private CancelShowReservationCommand cancelShowReservationCommand(String reservationId) {
    CancelShowReservationCommand cancelShowReservationCommand = new CancelShowReservationCommand();
    cancelShowReservationCommand.reservationId = reservationId;
    return cancelShowReservationCommand;
  }

  private CreateCinemaCommand createCinemaCommand() {
    CreateCinemaCommand createCinemaCommand = new CreateCinemaCommand();
    createCinemaCommand.name = "Cinema";
    createCinemaCommand.city = "Lublin";
    return createCinemaCommand;
  }

  private CreateMovieCommand createMovieCommand() {
    CreateMovieCommand createMovieCommand = new CreateMovieCommand();
    createMovieCommand.title = "Szczęki";
    createMovieCommand.description = "Se pływa rekin";
    return createMovieCommand;
  }

  private CreateShowCommand createShowCommand() {
    CreateShowCommand createShowCommand = new CreateShowCommand();
    createShowCommand.tickets =
        Map.of(
            "normal", new BigDecimal(20),
            "extra", new BigDecimal(30));

    CreateCinemaCommand createCinemaCommand = createCinemaCommand();
    createShowCommand.cinemaId = commandGateway.execute(createCinemaCommand);
    CreateMovieCommand createMovieCommand = createMovieCommand();
    createShowCommand.movieId = commandGateway.execute(createMovieCommand);
    createShowCommand.time = Instant.now().plus(2, ChronoUnit.DAYS);
    return createShowCommand;
  }

  private CreateShowReservationCommand createShowReservationCommand() {
    CreateShowReservationCommand createShowReservationCommand = new CreateShowReservationCommand();
    createShowReservationCommand.customer =
        Customer.builder()
            .email("adjasuidja@o2.pl")
            .firstName("Tomek")
            .lastName("Kowalski")
            .phoneNumer("423531642")
            .build();
    createShowReservationCommand.reservedSeats = Set.of(new Seat(2, 3), new Seat(4, 5));
    CreateShowCommand createShowCommand = createShowCommand();
    createShowReservationCommand.showId = commandGateway.execute(createShowCommand);
    createShowReservationCommand.tickets = Set.of(new Ticket("extra", 1), new Ticket("normal", 1));
    return createShowReservationCommand;
  }

  private CreatePayedShowReservationCommand createPayedShowReservationCommand() {
    CreatePayedShowReservationCommand createPayedShowReservationCommand =
        new CreatePayedShowReservationCommand();
    createPayedShowReservationCommand.reservedSeats = Set.of(new Seat(2, 3), new Seat(4, 5));
    CreateShowCommand createShowCommand = createShowCommand();
    createPayedShowReservationCommand.showId = commandGateway.execute(createShowCommand);
    createPayedShowReservationCommand.tickets =
        Set.of(new Ticket("extra", 1), new Ticket("normal", 1));
    return createPayedShowReservationCommand;
  }
}
