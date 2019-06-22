package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShowFactory {

    private final int cinemaHallRows = 20;
    private final int cinemaHallColumns = 30;

    public Show create(Cinema cinema, Movie movie, CreateShowCommand cmd) {
        return new Show(UUID.randomUUID(),
                movie,
                new CinemaHall(cinema, cinemaHallRows, cinemaHallColumns),
                cmd.time,
                cmd.tickets);
    }
}
