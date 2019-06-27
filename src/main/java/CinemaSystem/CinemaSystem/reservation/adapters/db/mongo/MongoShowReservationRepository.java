package CinemaSystem.CinemaSystem.reservation.adapters.db.mongo;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.exceptions.ShowReservationNotFoundException;
import org.modelmapper.ModelMapper;

public class MongoShowReservationRepository implements ShowReservationRepository {

  private final SpringDataMongoShowReservationRepository repository;
  private final ModelMapper modelMapper;
  private final ShowReservationFactory showFactory;

  public MongoShowReservationRepository(
      SpringDataMongoShowReservationRepository repository,
      ModelMapper modelMapper,
      ShowReservationFactory showFactory) {
    this.repository = repository;
    this.modelMapper = modelMapper;
    this.showFactory = showFactory;
  }

  @Override
  public ShowReservation get(String number) throws ShowReservationNotFoundException {
    ShowReservationMongoDto dto = repository.findById(number);
    if (dto == null) throw new ShowReservationNotFoundException();
    return convertToDomain(dto);
  }

  @Override
  public ShowReservation put(ShowReservation showReservation) {
    ShowReservationMongoDto dto = repository.save(convertToDto(showReservation));
    return convertToDomain(dto);
  }

  private ShowReservationMongoDto convertToDto(ShowReservation showReservation) {
    return modelMapper.map(showReservation, ShowReservationMongoDto.class);
  }

  private ShowReservation convertToDomain(ShowReservationMongoDto showReservationMongoDto) {
    return modelMapper.map(showReservationMongoDto, ShowReservation.class);
  }
}
