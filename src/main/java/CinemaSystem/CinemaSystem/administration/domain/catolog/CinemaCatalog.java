package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaCatalog {

  private final CinemaRepository cinemaRepository;

  @Autowired private ModelMapper modelMapper;

  @Autowired
  public CinemaCatalog(CinemaRepository cinemaRepository) {
    this.cinemaRepository = cinemaRepository;
  }

  public CinemaDto get(String id) {
    return convertCinemaToDto(cinemaRepository.get(id));
  }

  public List<CinemaDto> getAll() {
    return cinemaRepository.getAll().stream()
        .map(this::convertCinemaToDto)
        .collect(Collectors.toList());
  }

  private CinemaDto convertCinemaToDto(Cinema cinema) {
    return modelMapper.map(cinema, CinemaDto.class);
  }
}
