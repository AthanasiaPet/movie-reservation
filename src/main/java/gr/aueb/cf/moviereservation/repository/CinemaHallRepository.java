package gr.aueb.cf.moviereservation.repository;

import gr.aueb.cf.moviereservation.model.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
    Optional<CinemaHall> findByUuid(String uuid);
    boolean existsByHallName(String hallName);


}
