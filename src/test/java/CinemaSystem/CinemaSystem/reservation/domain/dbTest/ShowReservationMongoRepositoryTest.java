package CinemaSystem.CinemaSystem.reservation.domain.dbTest;

import CinemaSystem.CinemaSystem.reservation.domain.*;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShowReservationMongoRepositoryTest {

  @Autowired private ShowReservationRepository showReservationRepository;
  private ShowReservationFactory showReservationFactory = new ShowReservationFactory();

  private final String showReservationId = UUID.randomUUID().toString();
  private final String showId = UUID.randomUUID().toString();
  private final Customer customer = Customer.builder()
          .email("adjasuidja@o2.pl")
          .firstName("Tomek")
          .lastName("Kowalski")
          .phoneNumer("423531642")
          .build();
  private final TicketOrder ticket1 =
          TicketOrder.builder()
                  .type("normal")
                  .count(2)
                  .pricePerOne(new BigDecimal(20))
                  .totalPrice(new BigDecimal(40))
                  .build();
  private final Set<TicketOrder> tickets = Set.of(ticket1);
  private final Set<Seat> reservedSeats = Set.of(Seat.of(2, 3), Seat.of(4, 5));


  @Test
  void getsShowReservationFromMongo() {
    var createShowReservation = prepareCreateShowReservationCommand(showId, customer, reservedSeats);
    var showReservation = showReservationFactory.create(showReservationId, createShowReservation, tickets);

    var actualReservation = showReservationRepository.put(showReservation);

    assertThat(actualReservation.getId()).isEqualTo(showReservation.getId());
    assertThat(actualReservation.getShowId()).isEqualTo(showReservation.getShowId());
    assertThat(actualReservation.getShowReservationStatus()).isEqualTo(showReservation.getShowReservationStatus());
    assertThat(actualReservation.getTickets()).isEqualTo(showReservation.getTickets());
    assertThat(actualReservation.getCustomer()).isEqualTo(showReservation.getCustomer());
  }

  @Test
  void getsPayedShowReservationFromMongo() {
    var createShowReservation = prepareCreateShowReservationCommand(showId, new Customer(), reservedSeats);
    var showReservation = showReservationFactory.create(showReservationId, createShowReservation, tickets);

    var actualReservation = showReservationRepository.put(showReservation);

    assertThat(actualReservation.getId()).isEqualTo(showReservation.getId());
    assertThat(actualReservation.getShowId()).isEqualTo(showReservation.getShowId());
    assertThat(actualReservation.getShowReservationStatus()).isEqualTo(showReservation.getShowReservationStatus());
    assertThat(actualReservation.getTickets()).isEqualTo(showReservation.getTickets());
    assertThat(actualReservation.getCustomer()).isEqualTo(showReservation.getCustomer());
  }

  @Test
  void editsShowReservationFromMongo() {
    var createShowReservation = prepareCreateShowReservationCommand(showId, customer, reservedSeats);
    var showReservation = showReservationFactory.create(showReservationId, createShowReservation, tickets);
    var editedReservation = showReservationRepository.put(showReservation);
    editedReservation.cancel();

    var actualShowReservation = showReservationRepository.put(editedReservation);

    assertThat(showReservation.getId()).isEqualTo(actualShowReservation.getId());
    assertThat(showReservation.getShowId()).isEqualTo(actualShowReservation.getShowId());
    assertThat(showReservation.getShowReservationStatus())
        .isEqualTo(ShowReservationStatus.CONFIRMED);
    assertThat(actualShowReservation.getShowReservationStatus())
        .isEqualTo(ShowReservationStatus.CANCELED);
  }

  private CreateShowReservationCommand prepareCreateShowReservationCommand(String showId, Customer customer, Set<Seat> reservedSeats){
    CreateShowReservationCommand cmd = new CreateShowReservationCommand();
    cmd.showId = showId;
    cmd.customer = customer;
    cmd.reservedSeats = reservedSeats;
    return cmd;
  }
}
