package CinemaSystem.CinemaSystem.administration.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository {

  Show get(String number);

  Show put(Show show);

  List<Show> getAll();

  List<Show> getAllForMovieInCinema(String movieId, String cinemaId);

  List<Show> findAllByTime(LocalDateTime expirationTime);
}
