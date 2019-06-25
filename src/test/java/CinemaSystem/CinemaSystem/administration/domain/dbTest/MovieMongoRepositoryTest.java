package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieMongoRepositoryTest {

  @Autowired private MovieRepository movieRepository;

  private final String title = "Szczęki";
  private final String desc = "Se pływa rekin";

  @Test
  void shouldGetMovieFromMongo() {
    var movie =
        Movie.builder().id(UUID.randomUUID().toString()).title(title).description(desc).build();
    movieRepository.put(movie);

    var actualCinema = movieRepository.get(movie.getId());

    assertThat(actualCinema.getId()).isEqualTo(movie.getId());
    assertThat(actualCinema.getTitle()).isEqualTo(movie.getTitle());
    assertThat(actualCinema.getDescription()).isEqualTo(movie.getDescription());
  }

  @Test
  void shouldGetTwoMoviesFromMongo() {
    var movie1 =
        Movie.builder().id(UUID.randomUUID().toString()).title(title).description(desc).build();
    var movie2 =
        Movie.builder().id(UUID.randomUUID().toString()).title(title).description(desc).build();
    movieRepository.put(movie1);
    movieRepository.put(movie2);

    var actualMovies = movieRepository.getAll();

    assertThat(actualMovies).isNotNull();
    assertThat(actualMovies.size()).isEqualTo(2);
  }
}
