package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface SpringDataMongoMovieRepository extends Repository<MovieMongoDto, String> {

  void save(MovieMongoDto dto);

  MovieMongoDto findById(String id);

  List<MovieMongoDto> findAll();
}
