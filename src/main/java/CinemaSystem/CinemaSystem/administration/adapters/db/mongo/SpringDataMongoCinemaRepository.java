package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface SpringDataMongoCinemaRepository extends Repository<CinemaMongoDto, String> {

  CinemaMongoDto save(CinemaMongoDto dto);

  CinemaMongoDto findById(String id);

  List<CinemaMongoDto> findAll();
}
