package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.Cinema;
import CinemaSystem.CinemaSystem.administration.domain.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaCatalog {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaCatalog(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema get(UUID id){
        return cinemaRepository.get(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Cinema> getAll(){
        return cinemaRepository.getAll();
    }
}
