package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.SeatIsAlreadyBlockedException;
import lombok.Getter;

import java.util.Objects;

import static CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat.HallSeatStatus.BLOCKED;
import static CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat.HallSeatStatus.FREE;

@Getter
public class CinemaHallSeat {
  private int row;
  private int col;
  private HallSeatStatus status;

  public CinemaHallSeat(int row, int col) {
    this.row = row;
    this.col = col;
    this.status = FREE;
  }

  public void blockIfPossible() {
    if (status == FREE) status = BLOCKED;
    else throw new SeatIsAlreadyBlockedException("Seat is already blocked");
  }

  public void freeSeat() {
    status = FREE;
  }

  public boolean isFree(){
    return status == FREE;
  }

  public enum HallSeatStatus {
    BLOCKED,
    FREE
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CinemaHallSeat that = (CinemaHallSeat) o;
    return row == that.row &&
            col == that.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
