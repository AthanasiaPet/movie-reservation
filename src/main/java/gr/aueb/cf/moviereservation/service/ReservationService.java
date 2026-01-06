package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.enums.ReservationStatus;
import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.core.exceptions.ResourceNotFoundException;
import gr.aueb.cf.moviereservation.dto.ReservationCreateDTO;
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

    public Reservation createReservation(ReservationCreateDTO dto) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Screening screening = screeningRepository.findById(dto.screeningId()).orElseThrow(() -> new ResourceNotFoundException("Screening", "id", dto.screeningId()));

        if (reservationRepository.existsByScreening_IdAndSeatNumber(dto.screeningId(), dto.seatNumber())) {
            throw new ResourceAlreadyExistsException("Reservation", "seatNumber", dto.seatNumber());
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .screening(screening)
                .seatNumber(dto.seatNumber())
                .status(ReservationStatus.CREATED)
                .isActive(true)
                .build();


        return reservationRepository.save(reservation);


    }

    public List<Reservation> findMyReservations() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reservationRepository.findByUser_Id(user.getId());
    }

}
