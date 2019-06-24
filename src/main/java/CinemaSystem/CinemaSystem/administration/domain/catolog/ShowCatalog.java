package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowCatalog {

  private final ShowRepository showRepository;

  @Autowired ModelMapper modelMapper;

  @Autowired
  public ShowCatalog(ShowRepository movieRepository) {
    this.showRepository = movieRepository;
  }

  public ShowDto get(String id) {
    return convertShowToDto(showRepository.get(id).orElseThrow(ShowNotFoundException::new));
  }

  public List<ShowDto> getAll() {
    return showRepository.getAll().stream()
        .map(this::convertShowToDto)
        .collect(Collectors.toList());
  }

  public List<CinemaHallSeat> getFreeSeats(String id) {
    var show = showRepository.get(id).orElseThrow(ShowNotFoundException::new);
    return show.getCinemaHall().calculateFreeSeats();
  }

  private ShowDto convertShowToDto(Show show) {
    return modelMapper.map(show, ShowDto.class);
  }
}
