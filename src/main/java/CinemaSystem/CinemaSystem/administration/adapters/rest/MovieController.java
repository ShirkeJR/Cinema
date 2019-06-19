package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/movie")
public class MovieController {

  private final MovieRepository movieRepository;

  @Autowired
  public MovieController(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @PostMapping
  public String create(@RequestBody CreateMovieCommand cmd) {
    var movie =
        Movie.builder().id(UUID.randomUUID()).title(cmd.title).description(cmd.description).build();
    movieRepository.put(movie);
    return movie.getId().toString();
  }

  @GetMapping
  public List<Movie> getAll() {
    return movieRepository.getAll();
  }

  @GetMapping("/{id}")
  public Movie get(@PathVariable UUID id) {
    return movieRepository.get(id);
  }
}
