package CinemaSystem.CinemaSystem.administration.domain;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.util.*;

@Getter
@Embeddable
public class CinemaHall {

  private final int rows = 20;
  private final int columns = 30;

  @ManyToOne
  private Cinema cinema;

  @ElementCollection
  private List<Seat> seats = new ArrayList<>();

  public CinemaHall(Cinema cinema) {
    this.cinema = cinema;
    createCinemaHall();
  }

  private void createCinemaHall() {
    seats = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        seats.add(new Seat(i, j));
      }
    }
  }

  public void checkSeats() {


  }

  public void blockSeats() {


  }

  public final class Seat {
    private int row;
    private int col;
    private boolean blocked;

    public Seat(int row, int col) {
      this.row = row;
      this.col = col;
      this.blocked = false;
    }
  }
}
