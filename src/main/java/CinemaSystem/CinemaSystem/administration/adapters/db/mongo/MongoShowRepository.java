package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowFactory;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MongoShowRepository implements ShowRepository {

  private final SpringDataMongoShowRepository repository;
  private final ModelMapper modelMapper;
  private final ShowFactory showFactory;

  public MongoShowRepository(
      SpringDataMongoShowRepository repository, ModelMapper modelMapper, ShowFactory showFactory) {
    this.repository = repository;
    this.modelMapper = modelMapper;
    this.showFactory = showFactory;
  }

  @Override
  public Show get(String number) throws ShowNotFoundException {
    ShowMongoDto dto = repository.findById(number);
    if (dto == null) throw new ShowNotFoundException();
    return showFactory.addTicketCalculator(convertToDomain(dto));
  }

  @Override
  public Show put(Show show) {
    ShowMongoDto dto = repository.save(convertToDto(show));
    return showFactory.addTicketCalculator(convertToDomain(dto));
  }

  @Override
  public List<Show> getAllForMovieInCinema(String movieId, String cinemaId) {
    return this.repository.findAllByMovieIdAndAndCinemaId(movieId, cinemaId).stream()
        .map(this::convertToDomain)
        .map(showFactory::addTicketCalculator)
        .collect(Collectors.toList());
  }

  @Override
  public List<Show> findAllByTime(LocalDateTime expirationTime) {
    return repository.findAllByTime(expirationTime).stream()
            .map(this::convertToDomain)
            .map(showFactory::addTicketCalculator)
            .collect(Collectors.toList());
  }

  @Override
  public List<Show> getAll() {
    return repository.findAll().stream()
        .map(this::convertToDomain)
        .map(showFactory::addTicketCalculator)
        .collect(Collectors.toList());
  }

  private ShowMongoDto convertToDto(Show show) {
    return modelMapper.map(show, ShowMongoDto.class);
  }

  private Show convertToDomain(ShowMongoDto showMongoDto) {
    return modelMapper.map(showMongoDto, Show.class);
  }
}
