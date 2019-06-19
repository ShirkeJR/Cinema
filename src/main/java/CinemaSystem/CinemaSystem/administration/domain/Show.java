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
@Builder
public class Show {

  private UUID id;

  @ManyToOne
  private Movie movie;

  private CinemaHall cinemaHall;

  private Instant time;

  @ElementCollection
  private Map<String, BigDecimal> tickets;

  public boolean blockSeatsIfPossible(List<Seat> occupiedSeats) {
    cinemaHall.checkSeats();
    cinemaHall.blockSeats();
    return true;
  }

  public void unblockSeats(List<Seat> occupiedSeats) {


  }
}
