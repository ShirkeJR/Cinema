package CinemaSystem.CinemaSystem.administration.domain.commands;

import CinemaSystem.CinemaSystem.core.Command;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class CreateShowCommand implements Command {

  public UUID cinemaId;
  public UUID movieId;
  public Instant time;
  public Map<String, BigDecimal> tickets;
}
