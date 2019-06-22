package CinemaSystem.CinemaSystem.administration.domain.handler;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateCinemaCommand;
import CinemaSystem.CinemaSystem.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCinemaHandler implements Handler<CreateCinemaCommand, UUID> {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public UUID handle(CreateCinemaCommand cmd) {
        var cinema = Cinema.builder()
                .id(UUID.randomUUID())
                .name(cmd.name)
                .city(cmd.city)
                .build();
        cinemaRepository.put(cinema);
        return cinema.getId();
    }
}
