package gr.aueb.cf.moviereservation.repository;

import gr.aueb.cf.moviereservation.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    Optional<Screening> findByUuid(String uuid);
    List<Screening> findByMovie_Id(Long movieId);
    List<Screening> findByCinemaHall_Id(Long cinemaHallId);


}
