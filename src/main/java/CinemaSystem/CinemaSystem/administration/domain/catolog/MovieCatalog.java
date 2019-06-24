package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Movie;
import CinemaSystem.CinemaSystem.administration.domain.MovieRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCatalog {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieCatalog(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie get(String id){
        return movieRepository.get(id).orElseThrow(MovieNotFoundException::new);
    }

    public List<Movie> getAll(){
        return movieRepository.getAll();
    }
}
