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
    var movie =
        Movie.builder()
            .id(UUID.randomUUID().toString())
            .title(cmd.title)
            .description(cmd.description)
            .build();
    movieRepository.put(movie);
    return movie.getId();
  }
}
