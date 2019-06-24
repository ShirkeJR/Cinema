package CinemaSystem.CinemaSystem.administration.domain;

import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShowFactory {

    private final int cinemaHallRows = 20;
    private final int cinemaHallColumns = 30;
    private final TicketCalculator ticketCalculator;

    @Autowired
    public ShowFactory(TicketCalculator ticketCalculator) {
        this.ticketCalculator = ticketCalculator;
    }

    public Show create(Cinema cinema, Movie movie, CreateShowCommand cmd) {
        return new Show(UUID.randomUUID().toString(),
                movie,
                new CinemaHall(cinema, cinemaHallRows, cinemaHallColumns),
                cmd.time,
                cmd.tickets,
                ticketCalculator);
    }
}
