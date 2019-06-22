package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieCatalog {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieCatalog(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie get(UUID id){
        return movieRepository.get(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Movie> getAll(){
        return movieRepository.getAll();
    }
}
