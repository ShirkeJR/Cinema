package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;

import java.util.List;

public class CinemaCatalog {

  private final CinemaRepository cinemaRepository;

  public CinemaCatalog(CinemaRepository cinemaRepository) {
    this.cinemaRepository = cinemaRepository;
  }

  public Cinema get(String id) {
    return cinemaRepository.get(id);
  }

  public List<Cinema> getAll() {
    return cinemaRepository.getAll();
  }
}
