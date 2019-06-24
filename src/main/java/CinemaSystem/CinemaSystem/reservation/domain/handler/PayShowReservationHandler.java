package CinemaSystem.CinemaSystem.reservation.domain.handler;

import CinemaSystem.CinemaSystem.core.Handler;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.commands.PayShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayShowReservationHandler implements Handler<PayShowReservationCommand, String> {

    private final ShowReservationRepository showReservationRepository;

    @Autowired
    public PayShowReservationHandler(ShowReservationRepository showReservationRepository) {
        this.showReservationRepository = showReservationRepository;
    }

    @Override
    public String handle(PayShowReservationCommand cmd) {
        var showReservation = showReservationRepository.get(cmd.reservationId).
                orElseThrow(IllegalArgumentException::new);

        showReservation.pay();
        showReservationRepository.put(showReservation);
        return showReservation.getId();
    }
}
