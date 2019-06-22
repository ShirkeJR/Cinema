package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowCatalog {

    private final ShowRepository showRepository;

    @Autowired
    public ShowCatalog(ShowRepository movieRepository) {
        this.showRepository = movieRepository;
    }

    public Show get(UUID id){
        return showRepository.get(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Show> getAll(){
        return showRepository.getAll();
    }
}
