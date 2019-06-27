package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;

public interface CinemaRepository {

  Cinema get(String number);

  Cinema put(Cinema cinema);

  List<Cinema> getAll();
}
