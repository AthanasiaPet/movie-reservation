package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.service.CinemaHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema-halls")
@RequiredArgsConstructor
public class CinemaHallRestController {

    private final CinemaHallService cinemaHallService;

    @GetMapping
    public ResponseEntity<List<CinemaHall>> getAllHalls() {
        return ResponseEntity.ok(cinemaHallService.findAll());
    }

    @PostMapping
    public ResponseEntity<CinemaHall> createHall(@RequestBody CinemaHall hall) {
        return ResponseEntity.ok(cinemaHallService.save(hall));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaHall> getHallById(@PathVariable Long id) {
        return cinemaHallService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
