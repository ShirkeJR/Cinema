package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;

public interface ShowRepository {

  Optional<Show> get(String number);

  void put(Show show);

  List<Show> getAll();
}
