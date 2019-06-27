package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;

import java.util.Set;
import java.util.UUID;

public class CreatePayedShowReservationHandler
    implements Handler<CreatePayedShowReservationCommand, ShowReservation> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowReservationFactory showReservationFactory;
  private final ShowRepository showRepository;

  public CreatePayedShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    this.showReservationRepository = showReservationRepository;
    this.showReservationFactory = showReservationFactory;
    this.showRepository = showRepository;
  }

  @Override
  public ShowReservation handle(CreatePayedShowReservationCommand cmd) {
    var show = showRepository.get(cmd.showId);

    Set<TicketOrder> ticketOrders =
        show.calculateTicketsPerSeats(cmd.tickets, cmd.reservedSeats.size());

    var showReservation = showReservationFactory.createPayed(UUID.randomUUID().toString(), cmd, ticketOrders);
    showReservationRepository.put(showReservation);

    show.reserveReservation(showReservation);
    showRepository.put(show);

    return showReservation;
  }
}
