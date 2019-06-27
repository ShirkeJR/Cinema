package CinemaSystem.CinemaSystem.e2e;

import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

  private final Customer customer =
      Customer.builder()
          .email("adjasuidja@o2.pl")
          .firstName("Tomek")
          .lastName("Kowalski")
          .phoneNumer("423531642")
          .build();

  @Test
  public void createsCinema() {
    var createCinemaCommand = createCinemaCommand("Lublin", "Plaza");

    String id = commandGateway.execute(createCinemaCommand);

    var cinema = cinemaRepository.get(id);
    assertThat(cinema).isNotNull();
    assertThat(cinema.getId()).isEqualTo(id);
    assertThat(cinema.getName()).isEqualTo("Plaza");
    assertThat(cinema.getCity()).isEqualTo("Lublin");
  }

  @Test
  public void createsMovie() {
    var createMovieCommand = createMovieCommand("Szczęki", "Se rekiny");

    String id = commandGateway.execute(createMovieCommand);

    var movie = movieRepository.get(id);
    assertThat(movie).isNotNull();
    assertThat(movie.getId()).isEqualTo(id);
    assertThat(movie.getTitle()).isEqualTo("Szczęki");
    assertThat(movie.getDescription()).isEqualTo("Se rekiny");
  }

  @Test
  public void createsShow() {
    String cinemaId = commandGateway.execute(createCinemaCommand("Lublin", "Plaza"));
    String movieId = commandGateway.execute(createMovieCommand("Sczęki", "Se rekiny"));
    var createShowCommand =
        createShowCommand(
            cinemaId,
            movieId,
            LocalDateTime.now().plus(2, ChronoUnit.DAYS),
            Map.of("extra", new BigDecimal(20)));

    String id = commandGateway.execute(createShowCommand);

    var show = showRepository.get(id);
    assertThat(show).isNotNull();
    assertThat(show.getId()).isEqualTo(id);
    assertThat(show.getReservations().size()).isEqualTo(0);
    assertThat(show.getMovie().getId()).isEqualTo(createShowCommand.movieId);
    assertThat(show.getCinema().getId()).isEqualTo(createShowCommand.cinemaId);
    assertThat(show.getTicketPrices()).isEqualTo(createShowCommand.tickets);
    assertThat(show.getTime().getHour()).isEqualTo(createShowCommand.time.getHour());
  }

  @Test
  void createsShowReservation() {
    CreateShowReservationCommand createShowReservationCommand =
        createShowReservationCommand(customer);
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
    ShowReservation createdShowReservation =
        commandGateway.execute(createPayedShowReservationCommand);

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
    CreateShowReservationCommand createShowReservationCommand =
        createShowReservationCommand(customer);
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
    CreateShowReservationCommand createShowReservationCommand =
        createShowReservationCommand(customer);
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

  private CreateMovieCommand createMovieCommand(String title, String desc) {
    var cmd = new CreateMovieCommand();
    cmd.title = title;
    cmd.description = desc;
    return cmd;
  }

  private CreateCinemaCommand createCinemaCommand(String city, String name) {
    var cmd = new CreateCinemaCommand();
    cmd.city = city;
    cmd.name = name;
    return cmd;
  }

  private CreateShowCommand createShowCommand(
      String cinemaId, String movieId, LocalDateTime time, Map<String, BigDecimal> tickets) {
    var cmd = new CreateShowCommand();
    cmd.cinemaId = cinemaId;
    cmd.movieId = movieId;
    cmd.time = time;
    cmd.tickets = tickets;
    return cmd;
  }

  private CreateShowReservationCommand createShowReservationCommand(Customer customer) {
    CreateShowReservationCommand createShowReservationCommand = new CreateShowReservationCommand();
    createShowReservationCommand.customer = customer;
    createShowReservationCommand.reservedSeats = Set.of(new Seat(2, 3), new Seat(4, 5));
    String cinemaId = commandGateway.execute(createCinemaCommand("Lublin", "Plaza"));
    String movieId = commandGateway.execute(createMovieCommand("Szczęki", "Plaza"));
    CreateShowCommand createShowCommand =
        createShowCommand(
            cinemaId,
            movieId,
            LocalDateTime.now().plusDays(2L),
            Map.of("extra", new BigDecimal(20), "normal", new BigDecimal(10)));
    createShowReservationCommand.showId = commandGateway.execute(createShowCommand);
    createShowReservationCommand.tickets = Set.of(Ticket.of("extra", 1), Ticket.of("normal", 1));
    return createShowReservationCommand;
  }

  private CreatePayedShowReservationCommand createPayedShowReservationCommand() {
    CreatePayedShowReservationCommand createPayedShowReservationCommand =
        new CreatePayedShowReservationCommand();
    createPayedShowReservationCommand.reservedSeats = Set.of(new Seat(2, 3), new Seat(4, 5));
    String cinemaId = commandGateway.execute(createCinemaCommand("Lublin", "Plaza"));
    String movieId = commandGateway.execute(createMovieCommand("Szczęki", "Plaza"));
    CreateShowCommand createShowCommand =
        createShowCommand(
            cinemaId,
            movieId,
            LocalDateTime.now().plusDays(2L),
            Map.of("extra", new BigDecimal(20), "normal", new BigDecimal(10)));
    createPayedShowReservationCommand.showId = commandGateway.execute(createShowCommand);
    createPayedShowReservationCommand.tickets =
        Set.of(Ticket.of("extra", 1), Ticket.of("normal", 1));
    return createPayedShowReservationCommand;
  }
}
