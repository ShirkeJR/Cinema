package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository {

  Optional<Cinema> get(String number);

  void put(Cinema cinema);

  List<Cinema> getAll();
}
