package CinemaSystem.CinemaSystem.administration;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaHall;
import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateShowCommand;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShowTest {

    private final UUID id = UUID.randomUUID();
    private final Instant time = Instant.now().plus(2L, ChronoUnit.DAYS);
    private final Map<String, BigDecimal> tickets = Map.of(
            "normal",new BigDecimal(20),
            "extra", new BigDecimal(30));

    private final UUID cinemaId = UUID.randomUUID();
    private final String cinemaCity = "Lublin";
    private final String cinemaName = "Cinema";
    private final Cinema cinema = Cinema.builder()
            .id(cinemaId)
            .city(cinemaCity)
            .name(cinemaName)
            .build();
    private final CinemaHall cinemaHall = new CinemaHall(cinema, 20, 30);

    private final UUID movieId = UUID.randomUUID();
    private final String movieTitle = "Szczęki";
    private final String movieDesc = "Se pływa rekin";
    private final Movie movie = Movie.builder()
            .id(movieId)
            .title(movieTitle)
            .description(movieDesc)
            .build();

    @Test
    void createsShow() {
        var cmd = prepareCreateShowCommand();
        var show = new Show(id, movie, cinemaHall, cmd.time, cmd.tickets);

        assertThat(show.getId()).isEqualTo(id);
        assertThat(show.getCinemaHall()).isEqualTo(cinemaHall);
        assertThat(show.getMovie().getId()).isEqualTo(movieId);
        assertThat(show.getTime()).isEqualTo(time);
        assertThat(show.getTickets()).isEqualTo(tickets);
    }

    private CreateShowCommand prepareCreateShowCommand(){
        var cmd = new CreateShowCommand();
        cmd.cinemaId = cinemaId;
        cmd.movieId = movieId;
        cmd.time = time;
        cmd.tickets = tickets;
        return cmd;
    }
}
