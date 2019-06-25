package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.catolog.MovieCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/movie")
public class MovieController {

  private final CommandGateway commandGateway;
  private final MovieCatalog movieCatalog;
  private final ModelMapper modelMapper;

  public MovieController(
      CommandGateway commandGateway, MovieCatalog movieCatalog, ModelMapper modelMapper) {
    this.commandGateway = commandGateway;
    this.movieCatalog = movieCatalog;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody CreateMovieCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<MovieDto> getAll() {
    return movieCatalog.getAll().stream().map(this::convertMovieToDto).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public MovieDto get(@PathVariable String id) {
    return convertMovieToDto(movieCatalog.get(id));
  }

  private MovieDto convertMovieToDto(Movie movie) {
    return modelMapper.map(movie, MovieDto.class);
  }
}
