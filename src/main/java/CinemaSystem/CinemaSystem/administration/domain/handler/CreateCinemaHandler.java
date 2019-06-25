package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.core.Handler;

import java.util.UUID;

public class CreateCinemaHandler implements Handler<CreateCinemaCommand, String> {

  private final CinemaRepository cinemaRepository;

  public CreateCinemaHandler(CinemaRepository cinemaRepository) {
    this.cinemaRepository = cinemaRepository;
  }

  @Override
  public String handle(CreateCinemaCommand cmd) {
    var cinema =
        Cinema.builder().id(UUID.randomUUID().toString()).name(cmd.name).city(cmd.city).build();
    cinemaRepository.put(cinema);
    return cinema.getId();
  }
}
