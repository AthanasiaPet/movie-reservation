package gr.aueb.cf.moviereservation.service;


import gr.aueb.cf.moviereservation.core.exceptions.ResourceNotFoundException;
import gr.aueb.cf.moviereservation.dto.ScreeningCreateDTO;
import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.repository.CinemaHallRepository;
import gr.aueb.cf.moviereservation.repository.MovieRepository;
import gr.aueb.cf.moviereservation.repository.ScreeningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository cinemaHallRepository;

    @Transactional(readOnly = true)
    public List<Screening> findAll() {
        log.debug("Fetching all screenings");
        return screeningRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Screening> findByUuid(String uuid) {
        log.debug("Fetching screening with uuid={}", uuid);
        return screeningRepository.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public List<Screening> findByMovieId(Long movieId) {
        log.debug("Fetching screenings for movieId={}", movieId);
        return screeningRepository.findByMovie_Id(movieId);
    }

    @Transactional(readOnly = true)
    public List<Screening> findByCinemaHallId(Long cinemaHallId) {
        log.debug("Fetching screenings for cinemaHallId={}", cinemaHallId);
        return screeningRepository.findByCinemaHall_Id(cinemaHallId);
    }


    public Screening createScreening(ScreeningCreateDTO dto) {

        log.info("Creating screening for movieId={} in cinemaHallId={} at {}",
                dto.movieId(),
                dto.cinemaHallId(),
                dto.screeningDateTime()
        );

        Movie movie = movieRepository.findById(dto.movieId()).orElseThrow(() -> {
                    log.warn("Movie not found with id={}", dto.movieId());
                    return new ResourceNotFoundException("Movie", "id", dto.movieId());
                });

        CinemaHall hall = cinemaHallRepository.findById(dto.cinemaHallId()).orElseThrow(() -> {
                    log.warn("Cinema hall not found with id={}", dto.cinemaHallId());
                    return new ResourceNotFoundException("CinemaHall", "id", dto.cinemaHallId());
                });

        Screening screening = Screening.builder()
                .movie(movie)
                .cinemaHall(hall)
                .screeningDateTime(dto.screeningDateTime())
                .price(dto.price())
                .isActive(true)
                .build();

        Screening savedScreening = screeningRepository.save(screening);

        log.info("Screening with id={} for movieId={} in cinemaHallId={} created successfully",
                savedScreening.getId(),
                movie.getId(),
                hall.getId()
        );

        return savedScreening;
    }


}
