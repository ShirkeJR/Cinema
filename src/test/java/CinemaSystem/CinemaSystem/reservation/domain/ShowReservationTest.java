package CinemaSystem.CinemaSystem.reservation.domain;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShowReservationTest {

  private final String id = UUID.randomUUID().toString();
  private final String showId = UUID.randomUUID().toString();
  private final Seat reservedSeat1 = new Seat(2, 3);
  private final Seat reservedSeat2 = new Seat(4, 5);
  private final List<Seat> reservedSeats = List.of(reservedSeat1, reservedSeat2);
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
  private final Set<TicketOrder> tickets = Sets.newHashSet(ticket1, ticket2);
  private final String CustomerEmail = "adjasuidja@o2.pl";
  private final String CustomerFirstName = "Tomek";
  private final String CustomerLastName = "Kowalski";
  private final String CustomerPhoneNumber = "423531642";
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
    assertThat(showReservation.getCustomer().get().getFirstName()).isEqualTo(CustomerFirstName);
    assertThat(showReservation.getCustomer().get().getLastName()).isEqualTo(CustomerLastName);
    assertThat(showReservation.getCustomer().get().getEmail()).isEqualTo(CustomerEmail);
    assertThat(showReservation.getCustomer().get().getPhoneNumer()).isEqualTo(CustomerPhoneNumber);
    assertThat(showReservation.getReservedSeats()).isEqualTo(Sets.newHashSet(reservedSeats));
    assertThat(showReservation.getTickets()).isEqualTo(Sets.newHashSet(tickets));
    assertThat(showReservation.getShowReservationStatus()).isEqualTo(CONFIRMED);
  }

  @Test
  void createsPayedShowReservation() {
    var showReservation = createPayedShowReservation();

    assertThat(showReservation.getId()).isEqualTo(id);
    assertThat(showReservation.getShowId()).isEqualTo(showId);
    assertThat(showReservation.getCustomer()).isEqualTo(Optional.empty());
    assertThat(showReservation.getReservedSeats()).isEqualTo(Sets.newHashSet(reservedSeats));
    assertThat(showReservation.getTickets()).isEqualTo(Sets.newHashSet(tickets));
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
