package CinemaSystem.CinemaSystem.administration.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

public class CreateShowCommand implements Command {

  public String cinemaId;
  public String movieId;
  public Instant time;
  public Map<String, BigDecimal> tickets;
}
