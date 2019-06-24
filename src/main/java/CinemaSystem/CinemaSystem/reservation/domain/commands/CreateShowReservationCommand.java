package CinemaSystem.CinemaSystem.reservation.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;
import CinemaSystem.CinemaSystem.reservation.domain.Customer;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;

import java.util.Set;

public class CreateShowReservationCommand implements Command {

  public String showId;
  public Customer customer;
  public Set<Seat> reservedSeats;
  public Set<Ticket> tickets;
}
