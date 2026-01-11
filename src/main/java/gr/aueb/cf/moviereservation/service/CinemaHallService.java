package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.repository.CinemaHallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    @Transactional(readOnly = true)
    public List<CinemaHall> findAll() {
        log.debug("Fetching all cinema halls");
        return cinemaHallRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CinemaHall> findById(Long id) {
        log.debug("Fetching cinema hall with id={}", id);
        return cinemaHallRepository.findById(id);
    }


    public CinemaHall save(CinemaHall hall) {

        if (cinemaHallRepository.existsByHallName(hall.getHallName())) {
            log.warn("Attempt to create cinema hall with existing name={}", hall.getHallName());
            throw new ResourceAlreadyExistsException("CinemaHall", "hallName", hall.getHallName());
        }

        CinemaHall savedHall = cinemaHallRepository.save(hall);
        log.info("Cinema hall with id={} and name={} created successfully", savedHall.getId(), savedHall.getHallName());
        return savedHall;
    }

}
