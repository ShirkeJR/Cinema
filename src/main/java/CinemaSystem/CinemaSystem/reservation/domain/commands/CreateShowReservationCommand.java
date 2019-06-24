package CinemaSystem.CinemaSystem.reservation.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;
import CinemaSystem.CinemaSystem.reservation.domain.Customer;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;

import java.util.List;

public class CreateShowReservationCommand implements Command {

  public String showId;
  public Customer customer;
  public List<Seat> reservedSeats;
  public List<Ticket> tickets;
}
