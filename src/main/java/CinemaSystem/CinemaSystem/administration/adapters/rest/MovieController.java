package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.catolog.MovieCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/movie")
public class MovieController {

  private final CommandGateway commandGateway;
  private final MovieCatalog movieCatalog;

  @Autowired
  public MovieController(CommandGateway commandGateway, MovieCatalog movieCatalog) {
    this.commandGateway = commandGateway;
    this.movieCatalog = movieCatalog;
  }

  @PostMapping
  public String create(@RequestBody CreateMovieCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<Movie> getAll() {
    return movieCatalog.getAll();
  }

  @GetMapping("/{id}")
  public Movie get(@PathVariable UUID id) {
    return movieCatalog.get(id);
  }
}
