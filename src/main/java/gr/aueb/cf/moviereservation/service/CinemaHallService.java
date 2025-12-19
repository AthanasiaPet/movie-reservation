package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.repository.CinemaHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    public List<CinemaHall> findAll() {
        return cinemaHallRepository.findAll();
    }

    public Optional<CinemaHall> findById(Long id) {
        return cinemaHallRepository.findById(id);
    }


    public CinemaHall save(CinemaHall hall) {
        return cinemaHallRepository.save(hall);
    }
}
