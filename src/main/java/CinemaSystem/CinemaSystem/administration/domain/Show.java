package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Show {

  private UUID id;

  @ManyToOne
  private Movie movie;

  private CinemaHall cinemaHall;

  private Instant time;

  @ElementCollection
  private Map<String, BigDecimal> tickets;

  public Show(UUID id, Movie movie, CinemaHall cinemaHall, Instant time, Map<String, BigDecimal> tickets) {
    this.id = id;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.time = time;
    this.tickets = tickets;
  }

  public boolean blockSeatsIfPossible(List<Seat> occupiedSeats) {
    return true;
  }

  public void unblockSeats(List<Seat> occupiedSeats) {


  }
}
