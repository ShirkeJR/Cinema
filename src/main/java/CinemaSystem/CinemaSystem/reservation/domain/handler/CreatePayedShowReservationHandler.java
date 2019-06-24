package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CreatePayedShowReservationHandler
    implements Handler<CreatePayedShowReservationCommand, String> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowReservationFactory showReservationFactory;
  private final ShowRepository showRepository;

  @Autowired
  public CreatePayedShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    this.showReservationRepository = showReservationRepository;
    this.showReservationFactory = showReservationFactory;
    this.showRepository = showRepository;
  }

  @Transactional
  @Override
  public String handle(CreatePayedShowReservationCommand cmd) {
    var show = showRepository.get(cmd.showId);

    Set<TicketOrder> ticketOrders =
        show.calculateTicketsPerSeats(cmd.tickets, cmd.reservedSeats.size());

    var showReservation = showReservationFactory.createPayed(cmd, ticketOrders);
    showReservationRepository.put(showReservation);

    show.reserveReservation(showReservation);
    showRepository.put(show);

    return showReservation.getId();
  }
}
