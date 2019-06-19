package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.*;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/show")
public class ShowController {

  private final ShowRepository showRepository;
  private final MovieRepository movieRepository;
  private final CinemaRepository cinemaRepository;

  @Autowired
  public ShowController(
      ShowRepository showRepository,
      MovieRepository movieRepository,
      CinemaRepository cinemaRepository) {

    this.showRepository = showRepository;
    this.movieRepository = movieRepository;
    this.cinemaRepository = cinemaRepository;
  }

  @PostMapping
  public String create(@RequestBody CreateShowCommand cmd) {
    var cinema = cinemaRepository.get(cmd.cinemaId);
    var show = Show.builder()
            .id(UUID.randomUUID())
            .cinemaHall(new CinemaHall(cinema))
            .time(cmd.time)
            .movie(movieRepository.get(cmd.movieId))
            .tickets(cmd.tickets)
            .build();

    showRepository.put(show);
    return show.getId().toString();
  }

  @GetMapping
  public List<Show> getAll() {
    return showRepository.getAll();
  }

  @GetMapping("/{id}")
  public Show get(@PathVariable UUID id) {
    return showRepository.get(id);
  }
}
