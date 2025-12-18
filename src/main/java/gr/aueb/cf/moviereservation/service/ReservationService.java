package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.model.Reservation;
import gr.aueb.cf.moviereservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findByUuid(String uuid) {
        return reservationRepository.findByUuid(uuid);
    }

    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUser_Id(userId);
    }

    public List<Reservation> findByScreeningId(Long screeningId) {
        return reservationRepository.findByScreening_Id(screeningId);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}
