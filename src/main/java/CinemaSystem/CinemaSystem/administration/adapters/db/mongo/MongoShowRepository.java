package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowFactory;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
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
    if(dto == null) throw new ShowNotFoundException();
    return showFactory.createFromMongoDto(dto);
  }

  @Override
  public void put(Show show) {
    this.repository.save(convertToDto(show));
  }

  @Override
  public List<Show> getAllForMovieInCinema(String movieId, String cinemaId) {
    return this.repository.findAllByMovieIdAndAndCinemaId(movieId, cinemaId).stream()
            .map(showFactory::createFromMongoDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<Show> getAll() {
    return this.repository.findAll().stream()
        .map(showFactory::createFromMongoDto)
        .collect(Collectors.toList());
  }

  private ShowMongoDto convertToDto(Show show) {
    return modelMapper.map(show, ShowMongoDto.class);
  }
}
