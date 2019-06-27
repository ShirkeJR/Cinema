package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.SeatIsAlreadyBlockedException;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaHallTest {

  private final int CINEMA_HALL_ROWS = 10;
  private final int CINEMA_HALL_COLUMNS = 10;
  private CinemaHall cinemaHall;
  private final Seat seat1 = Seat.of(2, 3);
  private final Seat seat2 = Seat.of(4, 5);

  @BeforeEach
  void setUp() {
    cinemaHall = CinemaHall.of(CINEMA_HALL_ROWS, CINEMA_HALL_COLUMNS);
  }

  @Test
  void createsNewCinemaHall() {
    assertThat(cinemaHall.getRows()).isEqualTo(CINEMA_HALL_ROWS);
    assertThat(cinemaHall.getColumns()).isEqualTo(CINEMA_HALL_COLUMNS);
    assertThat(cinemaHall.getCinemaHallSeats().isEmpty()).isFalse();
    assertThat(cinemaHall.getCinemaHallSeats().size())
        .isEqualTo(cinemaHall.getRows() * cinemaHall.getColumns());
    cinemaHall.getCinemaHallSeats().forEach(seat -> assertThat(seat.isFree()).isTrue());
  }

  @Test
  void blocksTwoSeats() {
    Set<Seat> occupiedSeats = Set.of(seat1, seat2);

    cinemaHall.blockSeatsIfPossible(occupiedSeats);

    assertThat(cinemaHall.findSeat(seat1).isFree()).isFalse();
    assertThat(cinemaHall.findSeat(seat2).isFree()).isFalse();
    long countFreeSeats =
        cinemaHall.getCinemaHallSeats().stream().filter(CinemaHallSeat::isFree).count();
    assertThat(countFreeSeats).isEqualTo(cinemaHall.getCinemaHallSeats().size() - 2);
  }

  @Test
  void expectsInvalidSeatExceptionWhenNoSeatAtHall() {
    var unknownSeat = new Seat(100, 3);
    Set<Seat> occupiedSeats = Set.of(unknownSeat);

    Executable executable = () -> cinemaHall.blockSeatsIfPossible(occupiedSeats);

    Assertions.assertThrows(InvalidSeatException.class, executable);
  }

  @Test
  void expectsSeatIsAlreadyBlockedExceptionWhenTryToBlockAlreadyBlockedSeat() {
    Set<Seat> blockedSeats = Set.of(seat1);
    Set<Seat> occupiedSeats = Set.of(seat1);
    cinemaHall.blockSeatsIfPossible(blockedSeats);

    Executable executable = () -> cinemaHall.blockSeatsIfPossible(occupiedSeats);

    Assertions.assertThrows(SeatIsAlreadyBlockedException.class, executable);
  }

  @Test
  void unblocksTwoSeats() {
    Set<Seat> blockedSeats = Set.of(seat1, seat2);
    Set<Seat> unBlockedSeats = Set.of(seat1, seat2);
    cinemaHall.blockSeatsIfPossible(blockedSeats);

    cinemaHall.unBlockSeatsIfPossible(unBlockedSeats);

    assertThat(cinemaHall.findSeat(seat1).isFree()).isTrue();
    assertThat(cinemaHall.findSeat(seat2).isFree()).isTrue();
    long countFreeSeats =
        cinemaHall.getCinemaHallSeats().stream().filter(CinemaHallSeat::isFree).count();
    assertThat(countFreeSeats).isEqualTo(cinemaHall.getCinemaHallSeats().size());
  }

  @Test
  void calculatesFreeSeats() {
    Set<Seat> occupiedSeats = Set.of(seat1, seat2);
    cinemaHall.blockSeatsIfPossible(occupiedSeats);

    List<CinemaHallSeat> freeSeats = cinemaHall.calculateFreeSeats();

    assertThat(freeSeats.size())
        .isEqualTo(cinemaHall.getCinemaHallSeats().size() - occupiedSeats.size());
  }
}
