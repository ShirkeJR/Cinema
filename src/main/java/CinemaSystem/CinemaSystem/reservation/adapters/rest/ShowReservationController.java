package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.PayShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String create(@RequestBody CreateShowReservationCommand cmd) {
        return commandGateway.execute(cmd).toString();
    }

    @PostMapping("/{id}/pay")
    public String pay(@PathVariable UUID id, @RequestBody PayShowReservationCommand cmd) {
        return commandGateway.execute(cmd).toString();
    }

    @PostMapping("/create-payed")
    public String createPayed(@RequestBody CreatePayedShowReservationCommand cmd) {
        return commandGateway.execute(cmd).toString();
    }

    @PostMapping("/{id}/cancel")
    public void cancel(@PathVariable String id, @RequestBody CancelShowReservationCommand cmd) {
        commandGateway.execute(cmd);
    }

}
