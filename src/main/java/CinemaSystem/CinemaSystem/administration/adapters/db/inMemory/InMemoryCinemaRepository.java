package CinemaSystem.CinemaSystem.administration.adapters.db.inMemory;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.CinemaNotFoundException;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCinemaRepository implements CinemaRepository {

  private Map<String, Cinema> db = new HashMap<>();

  @Override
  public Cinema get(String id) {
    if (!db.containsKey(id)) {
      throw new CinemaNotFoundException(id);
    }
    return db.get(id);
  }

  @Override
  public Cinema put(Cinema cinema) {
    return db.put(cinema.getId(), cinema);
  }

  @Override
  public List<Cinema> getAll() {
    return Lists.newArrayList(db.values());
  }
}
