package CinemaSystem.CinemaSystem.administration.adapters.rest;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.Movie;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ShowDto {

  public String id;
  public Cinema cinema;
  public Movie movie;
  public LocalDateTime time;
  public Map<String, BigDecimal> ticketPrices;
}
