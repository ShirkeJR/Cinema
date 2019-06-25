package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class MongoMovieRepository implements MovieRepository {

  private final SpringDataMongoMovieRepository repository;
  private final ModelMapper modelMapper;

  public MongoMovieRepository(SpringDataMongoMovieRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Movie get(String number) throws MovieNotFoundException {
    MovieMongoDto dto = repository.findById(number);
    if(dto == null) throw new MovieNotFoundException();
    return Movie.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .description(dto.getDescription())
        .build();
  }

  @Override
  public void put(Movie movie) {
    this.repository.save(convertToDto(movie));
  }

  @Override
  public List<Movie> getAll() {
    return this.repository.findAll().stream()
        .map(
            dto ->
                Movie.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .build())
        .collect(Collectors.toList());
  }

  private MovieMongoDto convertToDto(Movie movie) {
    return modelMapper.map(movie, MovieMongoDto.class);
  }
}
