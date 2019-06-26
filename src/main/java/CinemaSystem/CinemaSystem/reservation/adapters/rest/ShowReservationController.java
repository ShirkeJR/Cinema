package CinemaSystem.CinemaSystem.reservation.adapters.rest;

import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CancelShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreatePayedShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.CreateShowReservationCommand;
import CinemaSystem.CinemaSystem.reservation.domain.commands.PayShowReservationCommand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "exeptions, content-type")
@RequestMapping("/api/reservation")
public class ShowReservationController {

  private final CommandGateway commandGateway;
  private final ModelMapper modelMapper;

  @Autowired
  public ShowReservationController(CommandGateway commandGateway, ModelMapper modelMapper) {
    this.commandGateway = commandGateway;
    this.modelMapper = modelMapper;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public CreatedShowReservationDto create(@RequestBody CreateShowReservationCommand cmd) {
    return convertToCreatedShowDto(commandGateway.execute(cmd));
  }

  @PreAuthorize("hasAnyRole('CASHIER', 'ADMIN')")
  @PostMapping("/{id}/pay")
  public String pay(@PathVariable UUID id, @RequestBody PayShowReservationCommand cmd) {
    return commandGateway.execute(cmd).toString();
  }

  @PreAuthorize("hasAnyRole('CASHIER', 'ADMIN')")
  @PostMapping("/createPayed")
  @ResponseStatus(HttpStatus.CREATED)
  public CreatedPayedShowReservationDto createPayed(@RequestBody CreatePayedShowReservationCommand cmd) {
    return convertToCreatedPayedShowDto(commandGateway.execute(cmd));
  }

  @PostMapping("/cancel/{id}")
  public String cancel(@PathVariable String id) {
    CancelShowReservationCommand cmd = new CancelShowReservationCommand();
    cmd.reservationId = id;
    return commandGateway.execute(cmd);
  }

  private CreatedShowReservationDto convertToCreatedShowDto(ShowReservation showReservation){
    CreatedShowReservationDto dto = modelMapper.map(showReservation, CreatedShowReservationDto.class);
    dto.setTotalCost(showReservation.calculateTotalPrice());
    return dto;
  }

  private CreatedPayedShowReservationDto convertToCreatedPayedShowDto(ShowReservation showReservation){
    CreatedPayedShowReservationDto dto = modelMapper.map(showReservation, CreatedPayedShowReservationDto.class);
    dto.setTotalCost(showReservation.calculateTotalPrice());
    return dto;
  }
}
