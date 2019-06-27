package CinemaSystem.CinemaSystem.reservation.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.exceptions.ShowReservationNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryShowReservationRepository implements ShowReservationRepository {

  private Map<String, ShowReservation> db = new HashMap<>();

  @Override
  public ShowReservation get(String id) {
    if (!db.containsKey(id)) {
      throw new ShowReservationNotFoundException(id);
    }
    return db.get(id);
  }

  @Override
  public ShowReservation put(ShowReservation showReservation) {
    return db.put(showReservation.getId(), showReservation);
  }
}
