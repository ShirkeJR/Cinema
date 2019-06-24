package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.catolog.CinemaCatalog;
import CinemaSystem.CinemaSystem.administration.domain.catolog.CinemaDto;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
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
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody CreateCinemaCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<CinemaDto> getAll() {
    return catalog.getAll();
  }

  @GetMapping("/{id}")
  public CinemaDto get(@PathVariable String id) {
    return catalog.get(id);
  }
}
