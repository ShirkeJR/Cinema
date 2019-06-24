package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.ShowFactory;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.CinemaNotFoundException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateShowHandler implements Handler<CreateShowCommand, String> {

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
  public String handle(CreateShowCommand cmd) {
    var cinema = cinemaRepository.get(cmd.cinemaId).orElseThrow(CinemaNotFoundException::new);
    var movie = movieRepository.get(cmd.movieId).orElseThrow(MovieNotFoundException::new);
    var show = showFactory.create(cinema, movie, cmd);
    showRepository.put(show);
    return show.getId();
  }
}
