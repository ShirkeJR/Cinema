package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaHall;
import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Document(collection = "shows")
public class ShowMongoDto {

  @Id private String id;

  private Cinema cinema;

  private Movie movie;

  private CinemaHall cinemaHall;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date time;

  private Map<String, BigDecimal> ticketPrices;

  private List<ShowReservation> reservations;
}
