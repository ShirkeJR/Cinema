package CinemaSystem.CinemaSystem.administration.adapters.db.mongo;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.CinemaNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
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
    return Cinema.builder().id(dto.getId()).name(dto.getName()).city(dto.getCity()).build();
  }

  @Override
  public void put(Cinema cinema) {
    this.repository.save(convertToDto(cinema));
  }

  @Override
  public List<Cinema> getAll() {
    return this.repository.findAll().stream()
        .map(
            dto -> Cinema.builder().id(dto.getId()).name(dto.getName()).city(dto.getCity()).build())
        .collect(Collectors.toList());
  }

  private CinemaMongoDto convertToDto(Cinema cinema) {
    return modelMapper.map(cinema, CinemaMongoDto.class);
  }
}
