package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.core.Handler;

import java.util.UUID;

public class CreateShowHandler implements Handler<CreateShowCommand, String> {

  private final ShowRepository showRepository;
  private final MovieRepository movieRepository;
  private final CinemaRepository cinemaRepository;
  private final ShowFactory showFactory;

  private final int CINEMAHALL_ROWS = 20;
  private final int CINEMAHALL_COLUMNS = 30;

  public CreateShowHandler(
      ShowRepository showRepository,
      MovieRepository movieRepository,
      ShowFactory showFactory,
      CinemaRepository cinemaRepository) {

    this.showRepository = showRepository;
    this.movieRepository = movieRepository;
    this.cinemaRepository = cinemaRepository;
    this.showFactory = showFactory;
  }

  @Override
  public String handle(CreateShowCommand cmd) {
    var movie = movieRepository.get(cmd.movieId);
    var cinema = cinemaRepository.get(cmd.cinemaId);
    var cinemaHall = CinemaHall.of(CINEMAHALL_ROWS, CINEMAHALL_COLUMNS);
    var show = showFactory.create(UUID.randomUUID().toString(), cinema, cinemaHall, movie, cmd);
    showRepository.put(show);
    return show.getId();
  }
}
