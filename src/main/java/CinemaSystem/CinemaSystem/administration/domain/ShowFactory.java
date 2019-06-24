package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShowFactory {

    private final int cinemaHallRows = 20;
    private final int cinemaHallColumns = 30;
    private final ShowTicketCalculator showTicketCalculator;

    @Autowired
    public ShowFactory(ShowTicketCalculator showTicketCalculator) {
        this.showTicketCalculator = showTicketCalculator;
    }

    public Show create(Cinema cinema, Movie movie, CreateShowCommand cmd) {
        return new Show(UUID.randomUUID().toString(),
                movie,
                new CinemaHall(cinema, cinemaHallRows, cinemaHallColumns),
                cmd.time,
                cmd.tickets,
                showTicketCalculator);
    }
}
