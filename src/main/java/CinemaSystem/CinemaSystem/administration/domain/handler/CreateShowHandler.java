package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateShowHandler implements Handler<CreateShowCommand, UUID> {

  private final ShowRepository showRepository;
  private final CinemaRepository cinemaRepository;
  private final MovieRepository movieRepository;
  private final ShowFactory showFactory;

  @Autowired
  public CreateShowHandler(
      ShowRepository showRepository,
      CinemaRepository cinemaRepository,
      MovieRepository movieRepository,
      ShowFactory showFactory) {

    this.showRepository = showRepository;
    this.cinemaRepository = cinemaRepository;
    this.movieRepository = movieRepository;
    this.showFactory = showFactory;
  }

  @Override
  public UUID handle(CreateShowCommand cmd) {
    var cinema = cinemaRepository.get(cmd.cinemaId).orElseThrow(IllegalArgumentException::new);
    var movie = movieRepository.get(cmd.movieId).orElseThrow(IllegalArgumentException::new);
    var show = showFactory.create(cinema, movie, cmd);
    showRepository.put(show);
    return show.getId();
  }
}
