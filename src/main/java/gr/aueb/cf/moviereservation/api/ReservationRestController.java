package gr.aueb.cf.moviereservation.api;


import gr.aueb.cf.moviereservation.dto.ReservationCreateDTO;
import gr.aueb.cf.moviereservation.dto.ReservationReadDTO;
import gr.aueb.cf.moviereservation.mapper.ReservationMapper;
import gr.aueb.cf.moviereservation.model.Reservation;
import gr.aueb.cf.moviereservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ReservationRestController {

    private final ReservationService reservationService;

    @Operation(
            summary = "Get all reservations",
            description = "Returns a list of all reservations. Requires authentication."
    )

    @GetMapping
    public ResponseEntity<List<ReservationReadDTO>> getAllReservations() {
        List<ReservationReadDTO> reservations = reservationService.findAll().stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }

    @Operation(
            summary = "Create a reservation",
            description = "Creates a new reservation for a specific screening and seat. "
    )

    @PostMapping
    public ResponseEntity<ReservationReadDTO> createReservation(@RequestBody ReservationCreateDTO dto) {
        Reservation saved = reservationService.createReservation(dto);
        return ResponseEntity.ok(ReservationMapper.toReadDTO(saved));
    }

    @Operation(
            summary = "Get reservation by UUID",
            description = "Returns a reservation identified by its UUID."
    )

    @GetMapping("/{uuid}")
    public ResponseEntity<ReservationReadDTO> getReservation(@PathVariable String uuid) {
        Reservation reservation = reservationService.findByUuid(uuid);
        return ResponseEntity.ok(ReservationMapper.toReadDTO(reservation));
    }


    @Operation(
            summary = "Get reservations by user",
            description = "Returns all reservations made by a specific user."
    )

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ReservationReadDTO>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationReadDTO> reservations = reservationService.findByUserId(userId).stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }

    @Operation(
            summary = "Get reservations by screening",
            description = "Returns all reservations for a specific screening."
    )

    @GetMapping("/by-screening/{screeningId}")
    public ResponseEntity<List<ReservationReadDTO>> getReservationsByScreening(@PathVariable Long screeningId) {
        List<ReservationReadDTO> reservations = reservationService.findByScreeningId(screeningId).stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }

    @Operation(
            summary = "Get my reservations",
            description = "Returns reservations of the currently authenticated user."
    )
    @GetMapping("/my")
    public ResponseEntity<List<ReservationReadDTO>> getMyReservations() {
        List<ReservationReadDTO> reservations = reservationService.findMyReservations().stream().map(ReservationMapper::toReadDTO).toList();
        return ResponseEntity.ok(reservations);
    }



}
