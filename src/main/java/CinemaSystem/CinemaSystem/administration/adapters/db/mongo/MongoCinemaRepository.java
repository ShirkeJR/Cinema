package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;

import java.util.List;
import java.util.Optional;

public class MongoCinemaRepository implements CinemaRepository {
    @Override
    public Optional<Cinema> get(String number) {
        return Optional.empty();
    }

    @Override
    public void put(Cinema cinema) {

    }

    @Override
    public List<Cinema> getAll() {
        return null;
    }
}
