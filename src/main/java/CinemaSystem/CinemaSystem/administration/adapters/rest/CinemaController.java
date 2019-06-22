package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.catolog.CinemaCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/cinema")
public class CinemaController {

  private final CommandGateway commandGateway;
  private final CinemaCatalog catalog;

  @Autowired
  public CinemaController(CommandGateway commandGateway, CinemaCatalog catalog) {
    this.commandGateway = commandGateway;
    this.catalog = catalog;
  }

  @PostMapping
  public String create(@RequestBody CreateCinemaCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<Cinema> getAll() {
    return catalog.getAll();
  }

  @GetMapping("/{id}")
  public Cinema get(@PathVariable UUID id) {
    return catalog.get(id);
  }
}
