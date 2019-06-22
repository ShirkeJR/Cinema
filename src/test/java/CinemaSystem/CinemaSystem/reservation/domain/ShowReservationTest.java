package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShowReservationTest {






    private ShowReservation createShowReservation() {
        var cmd = new CreateShowReservationCommand();
        cmd.showId = UUID.randomUUID();
        cmd.showReservationDetails = new ShowReservationDetails();
        cmd.occupiedSeats = List.of(new Seat(5,5), new Seat(5,6));

        return new ShowReservation(UUID.randomUUID(), cmd);
    }
}
