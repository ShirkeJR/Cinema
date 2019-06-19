package CinemaSystem.CinemaSystem.reservation.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationDetails;

import java.util.List;
import java.util.UUID;

public class CreateShowReservationCommand implements Command {

  public UUID showId;
  public ShowReservationDetails showReservationDetails;
  public List<Seat> occupiedSeats;
}
