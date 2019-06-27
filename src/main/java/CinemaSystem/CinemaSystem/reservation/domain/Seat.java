package CinemaSystem.CinemaSystem.reservation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Seat {

  private int row;
  private int col;

  public Seat(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public static Seat of(int row, int col) {
    return new Seat(row, col);
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
