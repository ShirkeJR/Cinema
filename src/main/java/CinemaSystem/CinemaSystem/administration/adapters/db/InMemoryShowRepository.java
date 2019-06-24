package CinemaSystem.CinemaSystem.administration.adapters.db;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryShowRepository implements ShowRepository {
  private Map<String, Show> db = new HashMap<>();

  @Override
  public Optional<Show> get(String id) {
    if (!db.containsKey(id)) {
      //throw new IllegalArgumentException("No show by Id");
    }
    return Optional.of(db.get(id));
  }

  @Override
  public void put(Show show) {
    db.put(show.getId(), show);
  }

  @Override
  public List<Show> getAll() {
    return Lists.newArrayList(db.values());
  }
}
