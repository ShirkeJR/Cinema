package CinemaSystem.CinemaSystem.administration.domain;

import java.util.List;

public interface MovieRepository {

  Movie get(String number);

  Movie put(Movie movie);

  List<Movie> getAll();
}
