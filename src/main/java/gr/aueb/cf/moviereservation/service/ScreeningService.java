package gr.aueb.cf.moviereservation.service;


import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

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

    public Screening save(Screening screening) {
        return screeningRepository.save(screening);
    }



}
