package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.queries.GetShowsForMovieInCinemaQuery;

import java.util.List;

public class ShowCatalog {

  private final ShowRepository showRepository;

  public ShowCatalog(ShowRepository showRepository) {
    this.showRepository = showRepository;
  }

  public Show get(String id) {
    return showRepository.get(id);
  }

  public List<Show> getAll() {
    return showRepository.getAll();
  }

  public List<Show> getAllForMovieInCinema(GetShowsForMovieInCinemaQuery query) {
    return showRepository.getAllForMovieInCinema(query.movieId, query.cinemaId);
  }

  public List<CinemaHallSeat> getFreeSeats(String id) {
    var show = showRepository.get(id);
    return show.getCinemaHall().calculateFreeSeats();
  }
}
