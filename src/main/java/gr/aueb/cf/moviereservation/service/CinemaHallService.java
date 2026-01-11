package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.repository.CinemaHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    @Transactional(readOnly = true)
    public List<CinemaHall> findAll() {
        return cinemaHallRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CinemaHall> findById(Long id) {
        return cinemaHallRepository.findById(id);
    }


    public CinemaHall save(CinemaHall hall) {

        if (cinemaHallRepository.existsByHallName(hall.getHallName())) {
            throw new ResourceAlreadyExistsException("CinemaHall", "hallName", hall.getHallName());
        }

        return cinemaHallRepository.save(hall);
    }

}
