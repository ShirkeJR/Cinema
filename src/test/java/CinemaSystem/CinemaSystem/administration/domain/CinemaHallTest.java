package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.exeptions.InvalidSeatException;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.SeatIsAlreadyBlockedException;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaHallTest {

  private final String cinemaId = UUID.randomUUID().toString();
  private final int CINEMA_HALL_ROWS = 10;
  private final int CINEMA_HALL_COLUMNS = 10;
  private CinemaHall cinemaHall;
  private final Seat seat1 = new Seat(2, 3);
  private final Seat seat2 = new Seat(4, 5);

  @BeforeEach
  void setUp() {
    cinemaHall = new CinemaHall(CINEMA_HALL_ROWS, CINEMA_HALL_COLUMNS);
  }

  @Test
  void createsCinemaHall() {
    assertThat(cinemaHall.getRows()).isEqualTo(CINEMA_HALL_ROWS);
    assertThat(cinemaHall.getColumns()).isEqualTo(CINEMA_HALL_COLUMNS);
    assertThat(cinemaHall.getCinemaHallSeats().isEmpty()).isFalse();
  }

  @Test
  void blocksTwoSeats() {
    Set<Seat> occupiedSeats = Sets.newHashSet(seat1, seat2);

    cinemaHall.blockSeatsIfPossible(occupiedSeats);

    assertThat(cinemaHall.findSeat(seat1).isFree()).isFalse();
    assertThat(cinemaHall.findSeat(seat2).isFree()).isFalse();
  }

  @Test
  void expectsInvalidSeatExceptionWhenNoSeatAtHall() {
    var unknownSeat = new Seat(100, 3);
    Set<Seat> occupiedSeats = Sets.newHashSet(unknownSeat);

    Executable executable = () -> cinemaHall.blockSeatsIfPossible(occupiedSeats);

    Assertions.assertThrows(InvalidSeatException.class, executable);
  }

  @Test
  void expectsSeatIsAlreadyBlockedExceptionWhenTryToBlockAlreadyBlockedSeat() {
    Set<Seat> blockedSeats = Sets.newHashSet(seat1);
    Set<Seat> occupiedSeats = Sets.newHashSet(seat1);
    cinemaHall.blockSeatsIfPossible(blockedSeats);

    Executable executable = () -> cinemaHall.blockSeatsIfPossible(occupiedSeats);

    Assertions.assertThrows(SeatIsAlreadyBlockedException.class, executable);
  }

  @Test
  void unblocksTwoSeats() {
    Set<Seat> blockedSeats = Sets.newHashSet(seat1, seat2);
    Set<Seat> unBlockedSeats = Sets.newHashSet(seat1, seat2);
    cinemaHall.blockSeatsIfPossible(blockedSeats);

    cinemaHall.unBlockSeatsIfPossible(unBlockedSeats);

    assertThat(cinemaHall.findSeat(seat1).isFree()).isTrue();
    assertThat(cinemaHall.findSeat(seat2).isFree()).isTrue();
  }

  @Test
  void calculatesFreeSeats() {
    Set<Seat> occupiedSeats = Sets.newHashSet(seat1, seat2);
    cinemaHall.blockSeatsIfPossible(occupiedSeats);

    List<CinemaHallSeat> freeSeats = cinemaHall.calculateFreeSeats();

    assertThat(freeSeats.size())
        .isEqualTo(cinemaHall.getCinemaHallSeats().size() - occupiedSeats.size());
  }
}
