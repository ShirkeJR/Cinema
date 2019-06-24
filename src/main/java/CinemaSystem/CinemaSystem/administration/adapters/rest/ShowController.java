package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.catolog.ShowCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/show")
public class ShowController {

  private final CommandGateway commandGateway;
  private final ShowCatalog showCatalog;

  @Autowired
  public ShowController(CommandGateway commandGateway, ShowCatalog showCatalog) {
    this.commandGateway = commandGateway;
    this.showCatalog = showCatalog;
  }

  @PostMapping
  public String create(@RequestBody CreateShowCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<Show> getAll() {
    return showCatalog.getAll();
  }

  @GetMapping("/{id}")
  public Show get(@PathVariable String id) {
    return showCatalog.get(id);
  }

  @GetMapping("/{id}/seats")
  public List<CinemaHallSeat> getFreeSeats(@PathVariable String id){
    return showCatalog.getFreeSeats(id);
  }
}
