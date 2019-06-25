package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.catolog.ShowCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.queries.GetShowsForMovieInCinemaQuery;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/show")
public class ShowController {

  private final CommandGateway commandGateway;
  private final ShowCatalog showCatalog;
  private final ModelMapper modelMapper;

  public ShowController(
      CommandGateway commandGateway, ShowCatalog showCatalog, ModelMapper modelMapper) {
    this.commandGateway = commandGateway;
    this.showCatalog = showCatalog;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody CreateShowCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping("/all")
  public List<ShowDto> getAll() {
    return showCatalog.getAll().stream().map(this::convertShowToDto).collect(Collectors.toList());
  }

  @PostMapping("/movie/cinema")
  public List<ShowDto> getAllForMovieInCinema(@RequestBody GetShowsForMovieInCinemaQuery query) {
    return showCatalog.getAllForMovieInCinema(query).stream()
        .map(this::convertShowToDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ShowDto get(@PathVariable String id) {
    return convertShowToDto(showCatalog.get(id));
  }

  @GetMapping("/{id}/seats")
  public List<CinemaHallSeat> getFreeSeats(@PathVariable String id) {
    return showCatalog.getFreeSeats(id);
  }

  private ShowDto convertShowToDto(Show show) {
    return modelMapper.map(show, ShowDto.class);
  }
}
