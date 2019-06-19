package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;

public class CloseShowReservationHandler implements Handler<CancelShowReservationCommand, String> {

    private final ShowReservationRepository showReservationRepository;
    private final ShowRepository showRepository;

    @Autowired
    public CloseShowReservationHandler(ShowReservationRepository showReservationRepository, ShowRepository showRepository) {
        this.showReservationRepository = showReservationRepository;
        this.showRepository = showRepository;
    }

    @Override
    public String handle(CancelShowReservationCommand cmd) {
        var showReservation = showReservationRepository.get(cmd.showReservationID);
        showReservation.cancel();
        var show = showRepository.get(showReservation.getShowId());
        show.unblockSeats(showReservation.getOccupiedSeats());
        showReservationRepository.put(showReservation);
        showRepository.put(show);
        return null;
    }
}
