package CinemaSystem.CinemaSystem.reservation.domain;

import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import lombok.Getter;

import java.util.*;

@Getter
public class ShowReservation {

    private final UUID reservationId;
    private final UUID showId;

    private ShowReservationDetails showReservationDetails;

    private ShowReservationStatus showReservationStatus;

    private List<Seat> occupiedSeats = new ArrayList<>();

    public ShowReservation(UUID id, CreateShowReservationCommand cmd) {
        this.reservationId = id;
        this.showId = cmd.showId;
        this.showReservationDetails = cmd.showReservationDetails;
        this.showReservationStatus = ShowReservationStatus.IN_PROGRESS;
    }

    public void reserve(Map<Seat, UUID> occupiedSeats) {

    }

    public void cancel() {

    }
}
