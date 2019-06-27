package CinemaSystem.CinemaSystem.administration.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMovieRepository implements MovieRepository {
  private Map<String, Movie> db = new HashMap<>();

  @Override
  public Movie get(String id) {
    if (!db.containsKey(id)) {
      throw new MovieNotFoundException(id);
    }
    return db.get(id);
  }

  @Override
  public Movie put(Movie movie) {
    return db.put(movie.getId(), movie);
  }

  @Override
  public List<Movie> getAll() {
    return Lists.newArrayList(db.values());
  }
}
