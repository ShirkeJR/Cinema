package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;

public interface ShowRepository {

  Show get(String number);

  void put(Show show);

  List<Show> getAll();
}
