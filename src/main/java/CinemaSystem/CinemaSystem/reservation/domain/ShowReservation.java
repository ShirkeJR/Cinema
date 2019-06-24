package CinemaSystem.CinemaSystem.reservation.domain;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus.*;

@Getter
public class ShowReservation {

  private final String id;
  private final String showId;

  private Optional<Customer> customer;

  private ShowReservationStatus showReservationStatus;

  private Set<Seat> reservedSeats;

  private Set<TicketOrder> tickets;

  public ShowReservation(
          String id, String showId, Customer customer, List<Seat> reservedSeats, Set<TicketOrder> tickets) {
    this.id = id;
    this.showId = showId;
    this.customer = Optional.of(customer);
    this.reservedSeats = Sets.newHashSet(reservedSeats);
    this.tickets = tickets;
    this.showReservationStatus = CONFIRMED;
  }

  public ShowReservation(String id, String showId, List<Seat> reservedSeats, Set<TicketOrder> tickets) {

    this.id = id;
    this.showId = showId;
    this.customer = Optional.empty();
    this.reservedSeats = Sets.newHashSet(reservedSeats);
    this.tickets = tickets;
    this.showReservationStatus = PAYED;
  }

  public void cancel() {
    showReservationStatus = CANCELED;
  }

  public void pay() {
    showReservationStatus = PAYED;
  }

  public BigDecimal calculateTotalPrice() {
    return tickets.stream()
        .map(TicketOrder::getTotalPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public ShowReservationStatus getShowReservationStatus() {
    return showReservationStatus;
  }

  public boolean isNotPayed() {
    return showReservationStatus != PAYED;
  }
}
