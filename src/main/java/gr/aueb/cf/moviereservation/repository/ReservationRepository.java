package gr.aueb.cf.moviereservation.repository;

import gr.aueb.cf.moviereservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUuid(String uuid);
    List<Reservation> findByUser_Id(Long userId);
    List<Reservation> findByScreening_Id(Long screeningId);

}
