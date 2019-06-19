package CinemaSystem.CinemaSystem.administration.adapters.db;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemoryCinemaRepository implements CinemaRepository {

  private Map<UUID, Cinema> db = new HashMap<>();

  @Override
  public Cinema get(UUID id) {
    if (!db.containsKey(id)) {
      throw new IllegalArgumentException("No cinema by Id");
    }
    return db.get(id);
  }

  @Override
  public void put(Cinema cinema) {
    db.put(cinema.getId(), cinema);
  }

  @Override
  public List<Cinema> getAll() {
    return Lists.newArrayList(db.values());
  }
}
