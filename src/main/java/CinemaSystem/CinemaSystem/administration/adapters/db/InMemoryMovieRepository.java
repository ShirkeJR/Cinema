package CinemaSystem.CinemaSystem.administration.adapters.db;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemoryMovieRepository implements MovieRepository {
  private Map<UUID, Movie> db = new HashMap<>();

  @Override
  public Movie get(UUID id) {
    if (!db.containsKey(id)) {
      throw new IllegalArgumentException("No movie by Id");
    }
    return db.get(id);
  }

  @Override
  public void put(Movie movie) {
    db.put(movie.getId(), movie);
  }

  @Override
  public List<Movie> getAll() {
    return Lists.newArrayList(db.values());
  }
}
