package CinemaSystem.CinemaSystem.administration.adapters.config;

import CinemaSystem.CinemaSystem.administration.adapters.db.mongo.*;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.ShowFactory;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.catolog.CinemaCatalog;
import CinemaSystem.CinemaSystem.administration.domain.catolog.MovieCatalog;
import CinemaSystem.CinemaSystem.administration.domain.catolog.ShowCatalog;
import CinemaSystem.CinemaSystem.administration.domain.handler.CreateCinemaHandler;
import CinemaSystem.CinemaSystem.administration.domain.handler.CreateMovieHandler;
import CinemaSystem.CinemaSystem.administration.domain.handler.CreateShowHandler;
import CinemaSystem.CinemaSystem.administration.domain.ticketCalculator.TicketCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Configuration
public class AdministrationConfig {

  @Bean
  public ShowFactory showFactory(TicketCalculator ticketCalculator) {
    return new ShowFactory(ticketCalculator);
  }

  @Bean
  public TicketCalculator ticketCalculator() {
    return new TicketCalculator();
  }

  @Bean
  public CreateCinemaHandler createCinemaHandler(CinemaRepository cinemaRepository) {
    return new CreateCinemaHandler(cinemaRepository);
  }

  @Bean
  public CreateMovieHandler createMovieHandler(MovieRepository movieRepository) {
    return new CreateMovieHandler(movieRepository);
  }

  @Bean
  public CreateShowHandler createShowHandler(
      ShowRepository showRepository,
      MovieRepository movieRepository,
      ShowFactory showFactory,
      CinemaRepository cinemaRepository) {
    return new CreateShowHandler(showRepository, movieRepository, showFactory, cinemaRepository);
  }

  @Bean
  public MovieCatalog movieCatalog(MovieRepository movieRepository) {
    return new MovieCatalog(movieRepository);
  }

  @Bean
  public CinemaCatalog cinemaCatalog(CinemaRepository cinemaRepository) {
    return new CinemaCatalog(cinemaRepository);
  }

  @Bean
  public ShowCatalog showCatalog(ShowRepository showRepository) {
    return new ShowCatalog(showRepository);
  }

  @Bean
  public CinemaRepository cinemaRepository(
      SpringDataMongoCinemaRepository springDataMongoCinemaRepository, ModelMapper mapper) {
    return new MongoCinemaRepository(springDataMongoCinemaRepository, mapper);
  }

  @Bean
  public MovieRepository movieRepository(
      SpringDataMongoMovieRepository springDataMongoMovieRepository, ModelMapper mapper) {
    return new MongoMovieRepository(springDataMongoMovieRepository, mapper);
  }

  @Bean
  public ShowRepository showRepository(
      SpringDataMongoShowRepository springDataMongoShowRepository,
      ModelMapper mapper,
      ShowFactory showFactory) {
    return new MongoShowRepository(springDataMongoShowRepository, mapper, showFactory);
  }
}
