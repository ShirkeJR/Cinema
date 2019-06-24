package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Seat {

  private final int row;
  private final int col;

  public Seat(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Seat seat = (Seat) o;
    return row == seat.row && col == seat.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
