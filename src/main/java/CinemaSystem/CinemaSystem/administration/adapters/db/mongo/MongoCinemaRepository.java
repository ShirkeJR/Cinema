package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.CinemaNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MongoCinemaRepository implements CinemaRepository {

  private final SpringDataMongoCinemaRepository repository;
  private final ModelMapper modelMapper;

  public MongoCinemaRepository(
      SpringDataMongoCinemaRepository repository, ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Cinema get(String number) throws CinemaNotFoundException {
    CinemaMongoDto dto = repository.findById(number);
    if (dto == null) throw new CinemaNotFoundException();
    return convertToDomain(dto);
  }

  @Override
  public Cinema put(Cinema cinema) {
    CinemaMongoDto dto = repository.save(convertToDto(cinema));
    return convertToDomain(dto);
  }

  @Override
  public List<Cinema> getAll() {
    return this.repository.findAll().stream()
        .map(this::convertToDomain)
        .collect(Collectors.toList());
  }

  private CinemaMongoDto convertToDto(Cinema cinema) {
    return modelMapper.map(cinema, CinemaMongoDto.class);
  }

  private Cinema convertToDomain(CinemaMongoDto cinemaMongoDto) {
    return modelMapper.map(cinemaMongoDto, Cinema.class);
  }
}
