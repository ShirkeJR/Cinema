package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.core.Handler;

import java.util.UUID;

public class CreateMovieHandler implements Handler<CreateMovieCommand, String> {

  private final MovieRepository movieRepository;

  public CreateMovieHandler(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public String handle(CreateMovieCommand cmd) {
    var movie = Movie.of(UUID.randomUUID().toString(), cmd);
    movieRepository.put(movie);
    return movie.getId();
  }
}
