package CinemaSystem.CinemaSystem.reservation.domain;

import java.util.UUID;

public interface ShowReservationRepository {

  void put(ShowReservation showReservation);

  ShowReservation get(UUID reservationId);
}
