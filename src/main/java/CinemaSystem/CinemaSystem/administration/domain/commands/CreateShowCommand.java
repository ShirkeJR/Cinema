package CinemaSystem.CinemaSystem.administration.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class CreateShowCommand implements Command {

  public String cinemaId;
  public String movieId;
  public LocalDateTime time;
  public Map<String, BigDecimal> tickets;
}
