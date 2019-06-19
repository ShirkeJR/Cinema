package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShowReservationFactory {

    public ShowReservationFactory() {

    }

    public ShowReservation create(CreateShowReservationCommand cmd) {
        return new ShowReservation(UUID.randomUUID(), cmd);
    }
}
