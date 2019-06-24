package CinemaSystem.CinemaSystem.reservation.adapters.db.mongo;

import org.springframework.data.repository.Repository;

public interface SpringDataMongoShowReservationRepository
    extends Repository<ShowReservationMongoDto, String> {

  void save(ShowReservationMongoDto dto);

  ShowReservationMongoDto findById(String id);
}
