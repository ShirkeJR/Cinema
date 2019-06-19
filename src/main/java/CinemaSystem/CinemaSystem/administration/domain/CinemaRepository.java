package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.UUID;

public interface CinemaRepository {

  Cinema get(UUID number);

  void put(Cinema cinema);

  List<Cinema> getAll();
}
