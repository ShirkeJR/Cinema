package CinemaSystem.CinemaSystem.reservation.domain.e2e;

import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.core.CommandGateway;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReservationTest {

  @Autowired private CommandGateway commandGateway;
  @Autowired private MovieRepository movieRepository;
  @Autowired private CinemaRepository cinemaRepository;
  @Autowired private ShowRepository showRepository;
  @Autowired private ShowReservationRepository showReservationRepository;

  @Test
  void createsShowReservation() {

  }

}
