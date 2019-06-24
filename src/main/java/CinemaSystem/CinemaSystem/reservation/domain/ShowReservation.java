package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.exceptions.InvalidShowReservationStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

import static CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowReservation {

  private String id;
  private String showId;

  private Customer customer;

  private ShowReservationStatus showReservationStatus;

  private Set<Seat> reservedSeats;

  private Set<TicketOrder> tickets;

  public ShowReservation(
      String id, String showId, Set<Seat> reservedSeats, Set<TicketOrder> tickets) {

    this.id = id;
    this.showId = showId;
    customer = new Customer();
    this.reservedSeats = reservedSeats;
    this.tickets = tickets;
    this.showReservationStatus = PAYED;
  }

  public ShowReservation(
      String id,
      String showId,
      Customer customer,
      Set<Seat> reservedSeats,
      Set<TicketOrder> tickets) {

    this.id = id;
    this.showId = showId;
    this.customer = customer;
    this.showReservationStatus = CONFIRMED;
    this.reservedSeats = reservedSeats;
    this.tickets = tickets;
  }

  public void cancel() {
    if (showReservationStatus == CANCELED) throw new InvalidShowReservationStatusException();
    showReservationStatus = CANCELED;
  }

  public void pay() {
    if (showReservationStatus == PAYED || showReservationStatus == CANCELED)
      throw new InvalidShowReservationStatusException();
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
    return showReservationStatus == CONFIRMED;
  }
}
