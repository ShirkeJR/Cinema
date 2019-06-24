package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCatalog {

  private final MovieRepository movieRepository;

  @Autowired private ModelMapper modelMapper;

  @Autowired
  public MovieCatalog(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public MovieDto get(String id) {
    return convertCinemaToDto(movieRepository.get(id));
  }

  public List<MovieDto> getAll() {
    return movieRepository.getAll().stream()
        .map(this::convertCinemaToDto)
        .collect(Collectors.toList());
  }

  private MovieDto convertCinemaToDto(Movie movie) {
    return modelMapper.map(movie, MovieDto.class);
  }
}
