package CinemaSystem.CinemaSystem.reservation.adapters.db.mongo;

import CinemaSystem.CinemaSystem.reservation.domain.Customer;
import CinemaSystem.CinemaSystem.reservation.domain.Seat;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationStatus;
import CinemaSystem.CinemaSystem.reservation.domain.TicketOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "showReservations")
public class ShowReservationMongoDto {

  @Id private String id;

  private String showId;

  private Customer customer;

  private ShowReservationStatus showReservationStatus;

  private Set<Seat> reservedSeats;

  private Set<TicketOrder> tickets;
}
