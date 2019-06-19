package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/reservation")
public class ShowReservationController {

    private CommandGateway commandGateway;

    @Autowired
    public ShowReservationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String create(@RequestBody CreateShowReservationCommand cmd) {
        return commandGateway.execute(cmd).toString();
    }

    @PostMapping("/{id}")
    public String selectSeats(@PathVariable UUID id, @RequestBody CreateShowReservationCommand cmd) {
        return commandGateway.execute(cmd).toString();
    }

    @PostMapping("/cancel/{id}")
    public void cancel(@PathVariable UUID id, @RequestBody CancelShowReservationCommand cmd) {
        commandGateway.execute(cmd);
    }

}
