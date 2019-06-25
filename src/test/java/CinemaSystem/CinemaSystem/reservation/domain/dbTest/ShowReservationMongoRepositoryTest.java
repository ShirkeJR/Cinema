package CinemaSystem.CinemaSystem.reservation.domain.dbTest;

import CinemaSystem.CinemaSystem.reservation.domain.Customer;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShowReservationMongoRepositoryTest {

  @Autowired private ShowReservationRepository showReservationRepository;

  private final String id1 = UUID.randomUUID().toString();
  private final String showId = UUID.randomUUID().toString();

  @Test
  void shouldGetShowReservationFromMongo() {
    var showReservation = new ShowReservation(id1, showId, Sets.newHashSet(), Sets.newHashSet());
    showReservationRepository.put(showReservation);

    var actualReservation = showReservationRepository.get(showReservation.getId());

    assertThat(actualReservation.getId()).isEqualTo(showReservation.getId());
    assertThat(actualReservation.getShowId()).isEqualTo(showReservation.getShowId());
    assertThat(actualReservation.getShowReservationStatus())
        .isEqualTo(showReservation.getShowReservationStatus());
  }

  @Test
  void shouldEditShowReservationFromMongo() {
    var showReservation =
        new ShowReservation(id1, showId, new Customer(), Sets.newHashSet(), Sets.newHashSet());
    showReservationRepository.put(showReservation);
    var editedReservation = showReservationRepository.get(showReservation.getId());
    editedReservation.cancel();
    showReservationRepository.put(editedReservation);

    var actualShowReservation = showReservationRepository.get(editedReservation.getId());

    assertThat(showReservation.getId()).isEqualTo(actualShowReservation.getId());
    assertThat(showReservation.getShowId()).isEqualTo(actualShowReservation.getShowId());
    assertThat(showReservation.getShowReservationStatus())
        .isEqualTo(ShowReservationStatus.CONFIRMED);
    assertThat(actualShowReservation.getShowReservationStatus())
        .isEqualTo(ShowReservationStatus.CANCELED);
  }
}
