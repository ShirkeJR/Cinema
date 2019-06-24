package CinemaSystem.CinemaSystem.administration.domain.catolog;

import CinemaSystem.CinemaSystem.administration.domain.CinemaHallSeat;
import CinemaSystem.CinemaSystem.administration.domain.Show;
import CinemaSystem.CinemaSystem.administration.domain.ShowRepository;
import CinemaSystem.CinemaSystem.administration.domain.exeptions.ShowNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowCatalog {

    private final ShowRepository showRepository;

    @Autowired
    public ShowCatalog(ShowRepository movieRepository) {
        this.showRepository = movieRepository;
    }

    public Show get(String id){
        return showRepository.get(id).orElseThrow(ShowNotFoundException::new);
    }

    public List<Show> getAll(){
        return showRepository.getAll();
    }

    public List<CinemaHallSeat> getFreeSeats(String id) {
        var show = get(id);
        return show.getCinemaHall().calculateFreeSeats();
    }
}
