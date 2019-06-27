package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;

import java.sql.Date;

public class ShowFactory {

  private final TicketCalculator ticketCalculator;

  public ShowFactory(TicketCalculator ticketCalculator) {
    this.ticketCalculator = ticketCalculator;
  }

  public Show create(
      String id, Cinema cinema, CinemaHall cinemaHall, Movie movie, CreateShowCommand cmd) {
    return new Show(id, movie, cinema, cinemaHall, cmd.time, cmd.tickets, ticketCalculator);
  }

  public Show addTicketCalculator(Show show) {
    show.setTicketCalculator(ticketCalculator);
    return show;
  }
}
