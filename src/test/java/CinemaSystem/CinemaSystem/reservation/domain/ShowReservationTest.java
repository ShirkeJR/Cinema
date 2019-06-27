package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.exceptions.InvalidShowReservationStatusException;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShowReservationTest {

  private final String id = UUID.randomUUID().toString();
  private final String showId = UUID.randomUUID().toString();
  private final Seat reservedSeat1 = new Seat(2, 3);
  private final Seat reservedSeat2 = new Seat(4, 5);
  private final Set<Seat> reservedSeats = Set.of(reservedSeat1, reservedSeat2);
  private final TicketOrder ticket1 =
      TicketOrder.builder()
          .type("normal")
          .count(1)
          .pricePerOne(new BigDecimal(20))
          .totalPrice(new BigDecimal(20))
          .build();
  private final TicketOrder ticket2 =
      TicketOrder.builder()
          .type("student")
          .count(1)
          .pricePerOne(new BigDecimal(15))
          .totalPrice(new BigDecimal(15))
          .build();
  private final Set<TicketOrder> tickets = Set.of(ticket1, ticket2);
  private final Customer customer =
      Customer.builder()
          .email("adjasuidja@o2.pl")
          .firstName("Tomek")
          .lastName("Kowalski")
          .phoneNumer("423531642")
          .build();

  @Test
  void createsShowReservation() {
    var showReservation = createShowReservation();

    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getShowId()).isEqualTo(showId);
    assertThat(showReservation.getCustomer().getFirstName()).isEqualTo(customer.getFirstName());
    assertThat(showReservation.getCustomer().getLastName()).isEqualTo(customer.getLastName());
    assertThat(showReservation.getCustomer().getEmail()).isEqualTo(customer.getEmail());
    assertThat(showReservation.getCustomer().getPhoneNumer()).isEqualTo(customer.getPhoneNumer());
    assertThat(showReservation.getReservedSeats().size()).isEqualTo(reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(tickets.size());
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(CONFIRMED);
  }

  @Test
  void createsPayedShowReservation() {
    var showReservation = createPayedShowReservation();

    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getShowId()).isEqualTo(showId);
    assertThat(showReservation.getCustomer()).isNotNull();
    assertThat(showReservation.getReservedSeats().size()).isEqualTo(reservedSeats.size());
    assertThat(showReservation.getTickets().size()).isEqualTo(tickets.size());
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(PAYED);
  }

  @Test
  void cancelsShowReservation() {
    var showReservation = createShowReservation();

    showReservation.cancel();

    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(CANCELED);
  }

  @Test
  void paysShowReservation() {
    var showReservation = createShowReservation();

    showReservation.pay();

    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(PAYED);
  }

  @Test
  void calculatesTotalCostOfTickets() {
    var showReservation = createShowReservation();

    BigDecimal totalCostOfTickets = showReservation.calculateTotalPrice();

    assertThat(totalCostOfTickets).isEqualTo(costOfThreeTickets(ticket1, ticket2));
  }

  @Test
  void expectsInvalidShowReservationStatusExceptionWhenPayForPayedReservation() {
    var showReservation = createPayedShowReservation();

    Executable executable = showReservation::pay;

    assertThrows(InvalidShowReservationStatusException.class, executable);
  }

  @Test
  void expectsInvalidShowReservationStatusExceptionWhenPayForCanceledReservation() {
    var showReservation = createShowReservation();
    showReservation.cancel();

    Executable executable = showReservation::pay;

    assertThrows(InvalidShowReservationStatusException.class, executable);
  }

  @Test
  void expectsInvalidShowReservationStatusExceptionWhenCancelCanceledReservation() {
    var showReservation = createShowReservation();
    showReservation.cancel();

    Executable executable = showReservation::cancel;

    assertThrows(InvalidShowReservationStatusException.class, executable);
  }

  private ShowReservation createShowReservation() {
    return new ShowReservation(id, showId, customer, reservedSeats, tickets);
  }

  private ShowReservation createPayedShowReservation() {
    return new ShowReservation(id, showId, reservedSeats, tickets);
  }

  private BigDecimal costOfThreeTickets(TicketOrder ticket1, TicketOrder ticket2) {
    return ticket1.getTotalPrice().add(ticket2.getTotalPrice());
  }
}
