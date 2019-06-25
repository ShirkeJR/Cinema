package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.adapters.db.mongo.ShowReservationMongoDto;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;

import java.util.Set;
import java.util.UUID;

public class ShowReservationFactory {

  public ShowReservationFactory() {}

  public ShowReservation create(
      CreateShowReservationCommand cmd, Set<TicketOrder> ticketOrderList) {
    return new ShowReservation(
        UUID.randomUUID().toString(), cmd.showId, cmd.customer, cmd.reservedSeats, ticketOrderList);
  }

  public ShowReservation createPayed(
      CreatePayedShowReservationCommand cmd, Set<TicketOrder> ticketOrders) {
    return new ShowReservation(
        UUID.randomUUID().toString(), cmd.showId, cmd.reservedSeats, ticketOrders);
  }

  public ShowReservation createFromMongoDto(ShowReservationMongoDto dto) {
    return new ShowReservation(
        dto.getId(),
        dto.getShowId(),
        dto.getCustomer(),
        dto.getShowReservationStatus(),
        dto.getReservedSeats(),
        dto.getTickets());
  }
}
