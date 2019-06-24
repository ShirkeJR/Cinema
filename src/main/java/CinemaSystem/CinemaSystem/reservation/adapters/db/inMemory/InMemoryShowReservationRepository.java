package CinemaSystem.CinemaSystem.reservation.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.exceptions.ShowReservationNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryShowReservationRepository implements ShowReservationRepository {

    private Map<String, ShowReservation> db = new HashMap<>();

    @Override
    public Optional<ShowReservation> get(String id) {
        if (!db.containsKey(id)) {
            throw new ShowReservationNotFoundException(id);
        }
        return Optional.of(db.get(id));
    }

    @Override
    public void put(ShowReservation showReservation) {
        db.put(showReservation.getId(), showReservation);
    }
}
