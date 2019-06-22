package CinemaSystem.CinemaSystem.administration;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaHall;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import java.util.UUID;

import static CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat.HallSeatStatus.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaHallTest {

  private final UUID cinemaId = UUID.randomUUID();
  private final String cinemaCity = "Lublin";
  private final String cinemaName = "Cinema";
  private final Cinema cinema =
      Cinema.builder().id(cinemaId).city(cinemaCity).name(cinemaName).build();
  private final int CINEMA_HALL_ROWS = 20;
  private final int CINEMA_HALL_COLUMNS = 30;
  private CinemaHall cinemaHall;
  private final Seat seat1 = new Seat(2, 3);
  private final Seat seat2 = new Seat(4, 5);

  @BeforeEach
  void setUp() {
    cinemaHall = new CinemaHall(cinema, CINEMA_HALL_ROWS, CINEMA_HALL_COLUMNS);
  }

  @Test
  void createsCinemaHall() {
    assertThat(cinemaHall.getCinema().getId()).isEqualTo(cinema.getId());
    assertThat(cinemaHall.getRows()).isEqualTo(CINEMA_HALL_ROWS);
    assertThat(cinemaHall.getColumns()).isEqualTo(CINEMA_HALL_COLUMNS);
    assertThat(cinemaHall.getCinemaHallSeats().isEmpty()).isFalse();
  }

  @Test
  void blocksTwoSeats() {
    List<Seat> occupiedSeats = List.of(seat1, seat2);

    cinemaHall.blockSeats(occupiedSeats);

    assertThat(cinemaHall.findSeat(seat1).getStatus()).isEqualTo(BLOCKED);
    assertThat(cinemaHall.findSeat(seat2).getStatus()).isEqualTo(BLOCKED);
  }

  @Test
  void expectsIllegalArgumentExeptionWhenNoSeatAtHall() {
    var unknownSeat = new Seat(100, 3);
    List<Seat> occupiedSeats = List.of(unknownSeat);

    Executable executable = () -> cinemaHall.blockSeats(occupiedSeats);

    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void expectsIllegalArgumentExeptionWhenTryToOccupBlockedSeat() {
    List<Seat> blockedSeats = List.of(seat1);
    List<Seat> occupiedSeats = List.of(seat1);
    cinemaHall.blockSeats(blockedSeats);

    Executable executable = () -> cinemaHall.blockSeats(occupiedSeats);

    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void freeTwoSeats() {
    List<Seat> blockedSeats = List.of(seat1, seat2);
    List<Seat> unBlockedSeats = List.of(seat1, seat2);
    cinemaHall.blockSeats(blockedSeats);

    cinemaHall.unBlockSeats(unBlockedSeats);

    assertThat(cinemaHall.findSeat(seat1).getStatus()).isEqualTo(FREE);
    assertThat(cinemaHall.findSeat(seat2).getStatus()).isEqualTo(FREE);
  }
}
