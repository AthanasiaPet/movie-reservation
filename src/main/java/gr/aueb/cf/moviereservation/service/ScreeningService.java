package gr.aueb.cf.moviereservation.service;


import gr.aueb.cf.moviereservation.core.exceptions.ResourceNotFoundException;
import gr.aueb.cf.moviereservation.dto.ScreeningCreateDTO;
import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.repository.CinemaHallRepository;
import gr.aueb.cf.moviereservation.repository.MovieRepository;
import gr.aueb.cf.moviereservation.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository cinemaHallRepository;

    public List<Screening> findAll() {
        return screeningRepository.findAll();
    }

    public Optional<Screening> findByUuid(String uuid) {
        return screeningRepository.findByUuid(uuid);
    }

    public List<Screening> findByMovieId(Long movieId) {
        return screeningRepository.findByMovie_Id(movieId);
    }

    public List<Screening> findByCinemaHallId(Long cinemaHallId) {
        return screeningRepository.findByCinemaHall_Id(cinemaHallId);
    }


    public Screening createScreening(ScreeningCreateDTO dto) {

        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", dto.movieId()));

        CinemaHall hall = cinemaHallRepository.findById(dto.cinemaHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Cinema hall", "id", dto.cinemaHallId()));

        Screening screening = Screening.builder()
                .movie(movie)
                .cinemaHall(hall)
                .screeningDateTime(dto.screeningDateTime())
                .price(dto.price())
                .isActive(true)
                .build();

        return screeningRepository.save(screening);
    }




}
