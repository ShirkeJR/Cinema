package CinemaSystem.CinemaSystem.reservation.domain;

import java.util.Optional;

public interface ShowReservationRepository {

  void put(ShowReservation showReservation);

  Optional<ShowReservation> get(String reservationId);
}
