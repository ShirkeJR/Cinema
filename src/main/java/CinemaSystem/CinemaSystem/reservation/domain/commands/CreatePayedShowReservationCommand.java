package CinemaSystem.CinemaSystem.reservation.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;

import java.util.Set;

public class CreatePayedShowReservationCommand implements Command {

  public String showId;
  public Set<Seat> reservedSeats;
  public Set<Ticket> tickets;
}
