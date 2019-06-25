package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatException;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CinemaHall {

  private int rows;
  private int columns;

  private Set<CinemaHallSeat> cinemaHallSeats = new HashSet<>();

  public CinemaHall(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    createCinemaHall(rows, columns);
  }

  private void createCinemaHall(int rows, int columns) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        cinemaHallSeats.add(new CinemaHallSeat(i, j));
      }
    }
  }

  public void blockSeatsIfPossible(Set<Seat> occupiedSeats) {
    occupiedSeats.forEach(seat -> findSeat(seat).blockIfPossible());
  }

  public CinemaHallSeat findSeat(Seat seat) {
    return cinemaHallSeats.stream()
        .filter(cinemaHallSeat -> isSearchedSeat(seat, cinemaHallSeat))
        .findFirst()
        .orElseThrow(InvalidSeatException::new);
  }

  private boolean isSearchedSeat(Seat seat, CinemaHallSeat cinemaHallSeat) {
    return cinemaHallSeat.getCol() == seat.getCol() && cinemaHallSeat.getRow() == seat.getRow();
  }

  public void unBlockSeatsIfPossible(Set<Seat> unBlockedSeats) {
    unBlockedSeats.forEach(seat -> findSeat(seat).unblockSeat());
  }

  public List<CinemaHallSeat> calculateFreeSeats() {
    return cinemaHallSeats.stream().filter(CinemaHallSeat::isFree).collect(Collectors.toList());
  }
}
