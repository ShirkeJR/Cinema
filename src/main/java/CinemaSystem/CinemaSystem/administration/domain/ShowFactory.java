package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.adapters.db.mongo.ShowMongoDto;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;

public class ShowFactory {

  private final TicketCalculator ticketCalculator;

  @Autowired
  public ShowFactory(TicketCalculator ticketCalculator) {
    this.ticketCalculator = ticketCalculator;
  }

  public Show create(
      String id,
      Cinema cinema,
      CinemaHall cinemaHall,
      Movie movie,
      Instant time,
      Map<String, BigDecimal> tickets) {
    return new Show(id, movie, cinema, cinemaHall, Date.from(time), tickets, ticketCalculator);
  }

  public Show createFromMongoDto(ShowMongoDto showMongoDto) {
    return new Show(
        showMongoDto.getId(),
        showMongoDto.getMovie(),
        showMongoDto.getCinema(),
        showMongoDto.getCinemaHall(),
        showMongoDto.getTime(),
        showMongoDto.getTicketPrices(),
        ticketCalculator,
        showMongoDto.getReservations());
  }
}
