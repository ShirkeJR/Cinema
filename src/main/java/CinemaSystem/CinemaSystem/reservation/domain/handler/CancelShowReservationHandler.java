package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelShowReservationHandler implements Handler<CancelShowReservationCommand, String> {

  private final ShowReservationRepository showReservationRepository;
  private final ShowRepository showRepository;

  @Autowired
  public CancelShowReservationHandler(
      ShowReservationRepository showReservationRepository, ShowRepository showRepository) {
    this.showReservationRepository = showReservationRepository;
    this.showRepository = showRepository;
  }

  @Override
  public String handle(CancelShowReservationCommand cmd) {
    var showReservation =
        showReservationRepository.get(cmd.reservationId).orElseThrow(IllegalArgumentException::new);
    var show =
        showRepository.get(showReservation.getShowId()).orElseThrow(IllegalArgumentException::new);

    show.unReserveReservation(showReservation);
    showRepository.put(show);

    showReservation.cancel();
    showReservationRepository.put(showReservation);
    return showReservation.getId();
  }
}
