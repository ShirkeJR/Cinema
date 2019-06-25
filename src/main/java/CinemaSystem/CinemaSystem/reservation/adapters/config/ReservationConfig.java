package CinemaSystem.CinemaSystem.reservation.adapters.config;

import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.reservation.adapters.db.mongo.MongoShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.adapters.db.mongo.SpringDataMongoShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.handler.CancelShowReservationHandler;
import CinemaSystem.CinemaSystem.reservation.domain.handler.CreatePayedShowReservationHandler;
import CinemaSystem.CinemaSystem.reservation.domain.handler.CreateShowReservationHandler;
import CinemaSystem.CinemaSystem.reservation.domain.handler.PayShowReservationHandler;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfig {

  @Bean
  public ShowReservationFactory showReservationFactory() {

    return new ShowReservationFactory();
  }

  @Bean
  public CancelShowReservationHandler cancelShowReservationHandler(
      ShowReservationRepository showReservationRepository, ShowRepository showRepository) {

    return new CancelShowReservationHandler(showReservationRepository, showRepository);
  }

  @Bean
  public CreateShowReservationHandler createShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    return new CreateShowReservationHandler(
        showReservationRepository, showReservationFactory, showRepository);
  }

  @Bean
  public CreatePayedShowReservationHandler createPayedShowReservationHandler(
      ShowReservationRepository showReservationRepository,
      ShowReservationFactory showReservationFactory,
      ShowRepository showRepository) {

    return new CreatePayedShowReservationHandler(
        showReservationRepository, showReservationFactory, showRepository);
  }

  @Bean
  public PayShowReservationHandler payShowReservationHandler(
      ShowReservationRepository showReservationRepository) {

    return new PayShowReservationHandler(showReservationRepository);
  }

  @Bean
  public ShowReservationRepository showReservationRepository(
      SpringDataMongoShowReservationRepository springDataMongoShowReservationRepository,
      ModelMapper mapper,
      ShowReservationFactory showReservationFactory) {

    return new MongoShowReservationRepository(
        springDataMongoShowReservationRepository, mapper, showReservationFactory);
  }
}
