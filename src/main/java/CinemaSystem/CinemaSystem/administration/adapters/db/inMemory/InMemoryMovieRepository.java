package CinemaSystem.CinemaSystem.administration.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryMovieRepository implements MovieRepository {
  private Map<String, Movie> db = new HashMap<>();

  @Override
  public Optional<Movie> get(String id) {
    if (!db.containsKey(id)) {
      throw new MovieNotFoundException(id);
    }
    return Optional.of(db.get(id));
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
