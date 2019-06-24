package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CreateShowReservationHandler implements Handler<CreateShowReservationCommand, String> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowReservationFactory showReservationFactory;
  private final ShowRepository showRepository;

  @Autowired
  public CreateShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    this.showReservationRepository = showReservationRepository;
    this.showReservationFactory = showReservationFactory;
    this.showRepository = showRepository;
  }

  @Transactional
  @Override
  public String handle(CreateShowReservationCommand cmd) {
    var show = showRepository.get(cmd.showId);

    Set<TicketOrder> ticketOrderList =
        show.calculateTicketsPerSeats(cmd.tickets, cmd.reservedSeats.size());

    var showReservation = showReservationFactory.create(cmd, ticketOrderList);
    showReservationRepository.put(showReservation);

    show.reserveReservation(showReservation);
    showRepository.put(show);

    return showReservation.getId();
  }
}
