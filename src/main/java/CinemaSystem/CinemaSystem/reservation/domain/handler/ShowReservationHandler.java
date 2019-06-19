package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShowReservationHandler implements Handler<CreateShowReservationCommand, UUID> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowReservationFactory showReservationFactory;
  private final ShowRepository showRepository;

  @Autowired
  public ShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    this.showReservationRepository = showReservationRepository;
    this.showReservationFactory = showReservationFactory;
    this.showRepository = showRepository;
  }

  public UUID handle(CreateShowReservationCommand cmd) {
    var show = showRepository.get(cmd.showId);
    if (!show.blockSeatsIfPossible(cmd.occupiedSeats)) {
      throw new IllegalArgumentException("No seats possible");
    }
    var showReservation = showReservationFactory.create(cmd);
    showReservationRepository.put(showReservation);
    showRepository.put(show);
    return showReservation.getReservationId();
  }
}
