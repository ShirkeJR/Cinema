package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowRepository {

  Optional<Show> get(UUID number);

  void put(Show show);

  List<Show> getAll();
}
