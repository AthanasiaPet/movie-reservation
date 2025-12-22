package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.model.Reservation;
import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.model.User;
import gr.aueb.cf.moviereservation.repository.ReservationRepository;
import gr.aueb.cf.moviereservation.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

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

    public Reservation createReservation(Long screeningId, String seatNumber) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Screening screening = screeningRepository.findById(screeningId).orElseThrow(() -> new RuntimeException("Screning not found"));
        Reservation reservation = Reservation.builder()
                .user(user)
                .screening(screening)
                .seatNumber(seatNumber)
                .isActive(true)
                .build();


        return reservationRepository.save(reservation);
    }

}
