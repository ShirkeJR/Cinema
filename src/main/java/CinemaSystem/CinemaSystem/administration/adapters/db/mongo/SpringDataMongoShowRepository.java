package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface SpringDataMongoShowRepository extends Repository<ShowMongoDto, String> {

  void save(ShowMongoDto dto);

  ShowMongoDto findById(String id);

  List<ShowMongoDto> findAll();

  List<ShowMongoDto> findAllByMovieIdAndAndCinemaId(String movieId, String cinemaId);
}
