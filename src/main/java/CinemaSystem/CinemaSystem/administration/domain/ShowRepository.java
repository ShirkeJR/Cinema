package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.UUID;

public interface ShowRepository {

  Show get(UUID number);

  void put(Show show);

  List<Show> getAll();
}
