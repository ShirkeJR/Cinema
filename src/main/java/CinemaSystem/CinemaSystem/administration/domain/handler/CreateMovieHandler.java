package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateMovieHandler implements Handler<CreateMovieCommand, UUID> {
  private final MovieRepository movieRepository;

  @Autowired
  public CreateMovieHandler(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public UUID handle(CreateMovieCommand cmd) {
    var movie = Movie.builder()
            .id(UUID.randomUUID())
            .title(cmd.title)
            .description(cmd.description)
            .build();
    movieRepository.put(movie);
    return movie.getId();
  }
}
