package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.catolog.MovieCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
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
  public Movie get(@PathVariable String id) {
    return movieCatalog.get(id);
  }
}
