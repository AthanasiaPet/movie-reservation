package gr.aueb.cf.moviereservation.api;


import gr.aueb.cf.moviereservation.dto.ReservationCreateDTO;
import gr.aueb.cf.moviereservation.dto.ReservationReadDTO;
import gr.aueb.cf.moviereservation.dto.ReservationRequestDTO;
import gr.aueb.cf.moviereservation.mapper.ReservationMapper;
import gr.aueb.cf.moviereservation.model.Reservation;
import gr.aueb.cf.moviereservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationRestController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationReadDTO>> getAllReservations() {
        List<ReservationReadDTO> reservations = reservationService.findAll().stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<ReservationReadDTO> createReservation(@RequestBody ReservationCreateDTO dto) {
        Reservation saved = reservationService.createReservation(dto);
        return ResponseEntity.ok(ReservationMapper.toReadDTO(saved));
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<ReservationReadDTO> getReservationByUuid(@PathVariable String uuid) {
        return reservationService.findByUuid(uuid)
                .map(ReservationMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ReservationReadDTO>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationReadDTO> reservations = reservationService.findByUserId(userId).stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/by-screening/{screeningId}")
    public ResponseEntity<List<ReservationReadDTO>> getReservationsByScreening(@PathVariable Long screeningId) {
        List<ReservationReadDTO> reservations = reservationService.findByScreeningId(screeningId).stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }


}
