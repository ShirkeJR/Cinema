package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;

import java.util.List;

public class MovieCatalog {

  private final MovieRepository movieRepository;

  public MovieCatalog(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public Movie get(String id) {
    return movieRepository.get(id);
  }

  public List<Movie> getAll() {
    return movieRepository.getAll();
  }
}
