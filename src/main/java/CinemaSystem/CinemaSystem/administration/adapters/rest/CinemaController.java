package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.catolog.CinemaCatalog;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/cinema")
public class CinemaController {

  private final CommandGateway commandGateway;
  private final CinemaCatalog catalog;
  private final ModelMapper modelMapper;

  public CinemaController(CommandGateway commandGateway, CinemaCatalog catalog, ModelMapper modelMapper) {
    this.commandGateway = commandGateway;
    this.catalog = catalog;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public String create(@RequestBody CreateCinemaCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @GetMapping
  public List<CinemaDto> getAll() {
    return catalog.getAll().stream()
            .map(this::convertCinemaToDto)
            .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public CinemaDto get(@PathVariable String id) {
    return convertCinemaToDto(catalog.get(id));
  }

  private CinemaDto convertCinemaToDto(Cinema cinema) {
    return modelMapper.map(cinema, CinemaDto.class);
  }

}
