package CinemaSystem.CinemaSystem.administration.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryShowRepository implements ShowRepository {
  private Map<String, Show> db = new HashMap<>();

  @Override
  public Show get(String id) {
    if (!db.containsKey(id)) {
      throw new ShowNotFoundException(id);
    }
    return db.get(id);
  }

  @Override
  public Show put(Show show) {
    return db.put(show.getId(), show);
  }

  @Override
  public List<Show> getAll() {
    return Lists.newArrayList(db.values());
  }

  @Override
  public List<Show> getAllForMovieInCinema(String movieId, String cinemaId) {
    return null;
  }

  @Override
  public List<Show> findAllByTime(LocalDateTime expirationTime) {
    return null;
  }
}
