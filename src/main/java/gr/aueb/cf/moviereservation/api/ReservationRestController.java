package gr.aueb.cf.moviereservation.api;


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
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.save(reservation));
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Reservation> getReservationByUuid(@PathVariable String uuid) {
        return reservationService.findByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.findByUserId(userId));
    }

    @GetMapping("/by-screening/{screeningId}")
    public ResponseEntity<List<Reservation>> getReservationsByScreening(@PathVariable Long screeningId) {
        return ResponseEntity.ok(reservationService.findByScreeningId(screeningId));
    }


}
