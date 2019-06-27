package CinemaSystem.CinemaSystem.administration.domain.dbTest;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.commands.CreateMovieCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieMongoRepositoryTest {

  @Autowired private MovieRepository movieRepository;
  @Autowired private MongoTemplate mongoTemplate;

  @BeforeEach
  void setUp() {
    mongoTemplate.dropCollection("movies");
  }

  @Test
  void shouldGetMovieFromMongo() {
    var movie = createMovie(UUID.randomUUID().toString(),"Szczęki", "Se pływa rekin");
    movieRepository.put(movie);

    var actualCinema = movieRepository.get(movie.getId());

    assertThat(actualCinema.getId()).isEqualTo(movie.getId());
    assertThat(actualCinema.getTitle()).isEqualTo(movie.getTitle());
    assertThat(actualCinema.getDescription()).isEqualTo(movie.getDescription());
  }

  @Test
  void shouldGetTwoMoviesFromMongo() {
    var movie1 = createMovie(UUID.randomUUID().toString(),"Szczęki", "Se pływa rekin");
    var movie2 = createMovie(UUID.randomUUID().toString(),"Szczęki2", "Se pływa rekin2");
    movieRepository.put(movie1);
    movieRepository.put(movie2);

    var actualMovies = movieRepository.getAll();

    assertThat(actualMovies).isNotNull();
    assertThat(actualMovies.size()).isEqualTo(2);
  }

  private Movie createMovie(String id, String title, String desc){
    var cmd = prepareCreateMovieCommand(title, desc);
    return Movie.of(id, cmd);
  }

  private CreateMovieCommand prepareCreateMovieCommand(String title, String desc) {
    var cmd = new CreateMovieCommand();
    cmd.title = title;
    cmd.description = desc;
    return cmd;
  }
}
