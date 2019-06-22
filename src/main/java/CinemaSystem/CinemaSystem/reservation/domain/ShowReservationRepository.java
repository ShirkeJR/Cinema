package CinemaSystem.CinemaSystem.reservation.domain;

import java.util.Optional;
import java.util.UUID;

public interface ShowReservationRepository {

  void put(ShowReservation showReservation);

  Optional<ShowReservation> get(UUID reservationId);
}
