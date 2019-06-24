package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

  Optional<Movie> get(String number);

  void put(Movie movie);

  List<Movie> getAll();
}
