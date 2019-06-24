package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateShowHandler implements Handler<CreateShowCommand, String> {

  private final ShowRepository showRepository;
  private final CinemaRepository cinemaRepository;
  private final MovieRepository movieRepository;
  private final ShowFactory showFactory;

  private final int CINEMAHALL_ROWS = 20;
  private final int CINEMAHALL_COLUMNS = 30;

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

  @Transactional
  @Override
  public String handle(CreateShowCommand cmd) {
    var cinema = cinemaRepository.get(cmd.cinemaId);
    var movie = movieRepository.get(cmd.movieId);
    var cinemaHall = new CinemaHall(cinema, CINEMAHALL_ROWS, CINEMAHALL_COLUMNS);
    var show =
        showFactory.create(UUID.randomUUID().toString(), cinemaHall, movie, cmd.time, cmd.tickets);
    showRepository.put(show);
    return show.getId();
  }
}
