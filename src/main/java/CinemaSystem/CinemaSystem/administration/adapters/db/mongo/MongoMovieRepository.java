package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

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
    if (dto == null) throw new MovieNotFoundException();
    return convertToDomain(dto);
  }

  @Override
  public Movie put(Movie movie) {
    MovieMongoDto dto = repository.save(convertToDto(movie));
    return convertToDomain(dto);
  }

  @Override
  public List<Movie> getAll() {
    return this.repository.findAll().stream()
        .map(this::convertToDomain)
        .collect(Collectors.toList());
  }

  private MovieMongoDto convertToDto(Movie movie) {
    return modelMapper.map(movie, MovieMongoDto.class);
  }

  private Movie convertToDomain(MovieMongoDto movieMongoDto) {
    return modelMapper.map(movieMongoDto, Movie.class);
  }
}
