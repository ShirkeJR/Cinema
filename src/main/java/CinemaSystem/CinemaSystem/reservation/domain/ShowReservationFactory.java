package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;

import java.util.Set;

public class ShowReservationFactory {

  public ShowReservationFactory() {}

  public ShowReservation create(
      String id, CreateShowReservationCommand cmd, Set<TicketOrder> ticketOrders) {
    return new ShowReservation(id, cmd.showId, cmd.customer, cmd.reservedSeats, ticketOrders);
  }

  public ShowReservation createPayed(
      String id, CreatePayedShowReservationCommand cmd, Set<TicketOrder> ticketOrders) {
    return new ShowReservation(id, cmd.showId, cmd.reservedSeats, ticketOrders);
  }
}
