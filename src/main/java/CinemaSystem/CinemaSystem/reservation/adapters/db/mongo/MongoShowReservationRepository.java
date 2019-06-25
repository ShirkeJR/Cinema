package CinemaSystem.CinemaSystem.reservation.adapters.db.mongo;

import CinemaSystem.CinemaSystem.reservation.domain.ShowReservation;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationFactory;
import CinemaSystem.CinemaSystem.reservation.domain.ShowReservationRepository;
import CinemaSystem.CinemaSystem.reservation.domain.exceptions.ShowReservationNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MongoShowReservationRepository implements ShowReservationRepository {

  private final SpringDataMongoShowReservationRepository repository;
  private final ModelMapper modelMapper;
  private final ShowReservationFactory showFactory;

  @Autowired
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
    if(dto == null) throw new ShowReservationNotFoundException();
    return showFactory.createFromMongoDto(dto);
  }

  @Override
  public void put(ShowReservation showReservation) {
    this.repository.save(convertToDto(showReservation));
  }

  private ShowReservationMongoDto convertToDto(ShowReservation showReservation) {
    return modelMapper.map(showReservation, ShowReservationMongoDto.class);
  }
}
