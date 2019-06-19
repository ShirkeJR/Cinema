package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/cinema")
public class CinemaController {

  private final CinemaRepository cinemaRepository;

  @Autowired
  public CinemaController(CinemaRepository cinemaRepository) {
    this.cinemaRepository = cinemaRepository;
  }

  @PostMapping
  public String create(@RequestBody CreateCinemaCommand cmd) {
    var cinema = Cinema.builder()
            .id(UUID.randomUUID())
            .name(cmd.name)
            .city(cmd.city)
            .build();
    cinemaRepository.put(cinema);
    return cinema.getId().toString();
  }

  @GetMapping
  public List<Cinema> getAll() {
    return cinemaRepository.getAll();
  }

  @GetMapping("/{id}")
  public Cinema get(@PathVariable UUID id) {
    return cinemaRepository.get(id);
  }
}
