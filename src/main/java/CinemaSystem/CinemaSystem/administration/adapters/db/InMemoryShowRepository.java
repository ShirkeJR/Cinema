package CinemaSystem.CinemaSystem.administration.adapters.db;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemoryShowRepository implements ShowRepository {
  private Map<UUID, Show> db = new HashMap<>();

  @Override
  public Show get(UUID id) {
    if (!db.containsKey(id)) {
      throw new IllegalArgumentException("No show by Id");
    }
    return db.get(id);
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
