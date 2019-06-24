package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.IllegalTicketCountException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatAndTicketCountException;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import lombok.Getter;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class Show {

  private String id;

  @ManyToOne private Movie movie;

  private CinemaHall cinemaHall;

  private Instant time;

  private Map<String, BigDecimal> ticketPrices;

  private TicketCalculator ticketCalculator;

  private List<ShowReservation> reservations = new ArrayList<>();

  public Show(
          String id,
          Movie movie,
          CinemaHall cinemaHall,
          Instant time,
          Map<String, BigDecimal> ticketPrices,
          TicketCalculator ticketCalculator) {
    this.id = id;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.time = time;
    this.ticketPrices = ticketPrices;
    this.ticketCalculator = ticketCalculator;
  }

  public void reserveReservation(ShowReservation reservation) {
    cinemaHall.blockSeatsIfPossible(reservation.getReservedSeats());
    reservations.add(reservation);
  }

  public void unReserveReservation(ShowReservation showReservation) {
    cinemaHall.unBlockSeatsIfPossible(showReservation.getReservedSeats());
    reservations.remove(showReservation);
  }

  public Set<TicketOrder> calculateTicketsPerSeats(List<Ticket> tickets, int seatsCount) {
    validateTicketsAndSeatsCount(tickets, seatsCount);
    return ticketCalculator.calculateTickets(ticketPrices, tickets);
  }

  private void validateTicketsAndSeatsCount(List<Ticket> tickets, int seatsCount) {
    int ticketsCount =
        tickets.stream()
            .map(Ticket::getCount)
            .reduce(Integer::sum)
            .orElseThrow(IllegalTicketCountException::new);

    if (seatsCount != ticketsCount) {
      throw new InvalidSeatAndTicketCountException();
    }
  }

  private void cancelNotPayedReservations() {
    reservations.forEach(
        reservation -> {
          if (reservation.isNotPayed()) {
            unReserveReservation(reservation);
            reservation.cancel();
          }
        });
  }
}
