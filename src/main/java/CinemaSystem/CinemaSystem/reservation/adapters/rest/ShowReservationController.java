package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.PayShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/reservation")
public class ShowReservationController {

  private CommandGateway commandGateway;

  @Autowired
  public ShowReservationController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody CreateShowReservationCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @PostMapping("/{id}/pay")
  public String pay(@PathVariable UUID id, @RequestBody PayShowReservationCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @PostMapping("/create-payed")
  @ResponseStatus(HttpStatus.CREATED)
  public String createPayed(@RequestBody CreatePayedShowReservationCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @PostMapping("/{id}/cancel")
  public String cancel(@PathVariable String id, @RequestBody CancelShowReservationCommand cmd) {
    return commandGateway.execute(cmd);
  }
}
