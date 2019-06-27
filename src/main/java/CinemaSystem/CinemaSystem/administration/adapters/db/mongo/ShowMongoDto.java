package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHall;
import CinemaSystem.CinemaSystem.reservation.adapters.db.mongo.ShowReservationMongoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "shows")
public class ShowMongoDto {

  @Id private String id;

  private CinemaMongoDto cinema;

  private MovieMongoDto movie;

  private CinemaHall cinemaHall;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime time;

  private Map<String, BigDecimal> ticketPrices;

  @DBRef
  private List<ShowReservationMongoDto> reservations;
}
