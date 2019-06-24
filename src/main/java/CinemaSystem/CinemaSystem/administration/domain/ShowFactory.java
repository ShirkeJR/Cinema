package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.adapters.db.mongo.ShowMongoDto;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;

@Component
public class ShowFactory {

  private final TicketCalculator ticketCalculator;

  @Autowired
  public ShowFactory(TicketCalculator ticketCalculator) {
    this.ticketCalculator = ticketCalculator;
  }

  public Show create(
      String id,
      CinemaHall cinemaHall,
      Movie movie,
      Instant time,
      Map<String, BigDecimal> tickets) {
    return new Show(id, movie, cinemaHall, Date.from(time), tickets, ticketCalculator);
  }

  public Show createFromMongoDto(ShowMongoDto showMongoDto) {
    return new Show(
        showMongoDto.getId(),
        showMongoDto.getMovie(),
        showMongoDto.getCinemaHall(),
        showMongoDto.getTime(),
        showMongoDto.getTicketPrices(),
        ticketCalculator,
        showMongoDto.getReservations());
  }
}
