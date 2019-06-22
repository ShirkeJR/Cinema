package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CinemaRepository {

  Optional<Cinema> get(UUID number);

  void put(Cinema cinema);

  List<Cinema> getAll();
}
