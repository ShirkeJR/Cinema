package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ShowDto {

  public String id;
  public Movie movie;
  public Date time;
  public Map<String, BigDecimal> ticketPrices;
}
