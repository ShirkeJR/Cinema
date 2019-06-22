package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {

  Optional<Movie> get(UUID number);

  void put(Movie movie);

  List<Movie> getAll();
}
