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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;


    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        log.debug("Fetching all reservations");
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Reservation> findByUuid(String uuid) {
        log.debug("Fetching reservation with uuid={}", uuid);
        return reservationRepository.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findByUserId(Long userId) {
        log.debug("Fetching reservations for userId={}", userId);
        return reservationRepository.findByUser_Id(userId);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findByScreeningId(Long screeningId) {
        log.debug("Fetching reservations for screeningId={}", screeningId);
        return reservationRepository.findByScreening_Id(screeningId);
    }

    public Reservation createReservation(ReservationCreateDTO dto) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        log.info("User id={} attempting to create reservation for screeningId={} seat={}",
                user.getId(), dto.screeningId(), dto.seatNumber());

        Screening screening = screeningRepository.findById(dto.screeningId()).orElseThrow(() -> new ResourceNotFoundException("Screening", "id", dto.screeningId()));

        if (reservationRepository.existsByScreening_IdAndSeatNumber(dto.screeningId(), dto.seatNumber())) {
            log.warn("Seat {} already reserved for screeningId={}", dto.seatNumber(), dto.screeningId());
            throw new ResourceAlreadyExistsException("Reservation", "seatNumber", dto.seatNumber());
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .screening(screening)
                .seatNumber(dto.seatNumber())
                .status(ReservationStatus.CREATED)
                .isActive(true)
                .build();


        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Reservation with id={} for userId={} screeningId={} seat={} created successfully",
                savedReservation.getId(),
                user.getId(),
                dto.screeningId(),
                dto.seatNumber()
        );

        return savedReservation;
    }

    @Transactional(readOnly = true)
    public List<Reservation> findMyReservations() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("Fetching reservations for current user id={}", user.getId());
        return reservationRepository.findByUser_Id(user.getId());
    }

}
