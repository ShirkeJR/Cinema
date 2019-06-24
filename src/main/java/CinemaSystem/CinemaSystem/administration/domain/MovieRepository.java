package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;

public interface MovieRepository {

  Movie get(String number);

  void put(Movie movie);

  List<Movie> getAll();
}
