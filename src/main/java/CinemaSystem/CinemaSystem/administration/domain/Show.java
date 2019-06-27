package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.IllegalTicketCountException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatAndTicketCountException;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.Ticket;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Show {

  private String id;

  private Movie movie;

  private Cinema cinema;

  private CinemaHall cinemaHall;

  private LocalDateTime time;

  private Map<String, BigDecimal> ticketPrices;

  private TicketCalculator ticketCalculator;

  private List<ShowReservation> reservations = new ArrayList<>();

  public Show(
      String id,
      Movie movie,
      Cinema cinema,
      CinemaHall cinemaHall,
      LocalDateTime time,
      Map<String, BigDecimal> ticketPrices,
      TicketCalculator ticketCalculator) {

    this.id = id;
    this.movie = movie;
    this.cinema = cinema;
    this.cinemaHall = cinemaHall;
    this.time = time;
    this.ticketPrices = ticketPrices;
    this.ticketCalculator = ticketCalculator;
    this.reservations = new ArrayList<>();
  }

  public void reserveReservation(ShowReservation reservation) {
    cinemaHall.blockSeatsIfPossible(reservation.getReservedSeats());
    reservations.add(reservation);
  }

  public void unReserveReservation(ShowReservation showReservation) {
    cinemaHall.unBlockSeatsIfPossible(showReservation.getReservedSeats());
    reservations.remove(showReservation);
  }

  public Set<TicketOrder> calculateTicketsPerSeats(Set<Ticket> tickets, int seatsCount) {
    validateTicketsAndSeatsCount(tickets, seatsCount);
    return ticketCalculator.calculateTickets(ticketPrices, tickets);
  }

  private void validateTicketsAndSeatsCount(Set<Ticket> tickets, int seatsCount) {
    int ticketsCount =
        tickets.stream()
            .map(Ticket::getCount)
            .reduce(Integer::sum)
            .orElseThrow(IllegalTicketCountException::new);

    if (seatsCount != ticketsCount) {
      throw new InvalidSeatAndTicketCountException();
    }
  }
}
