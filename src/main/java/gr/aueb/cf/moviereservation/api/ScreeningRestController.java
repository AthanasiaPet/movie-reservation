package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
@RequiredArgsConstructor
public class ScreeningRestController {

    private final ScreeningService screeningService;

    @GetMapping
    public ResponseEntity<List<Screening>> getAllScreenings() {
        return ResponseEntity.ok(screeningService.findAll());
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Screening> getScreeningByUuid(@PathVariable String uuid) {
        return screeningService.findByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-movie/{movieId}")
    public ResponseEntity<List<Screening>> getScreeningsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(screeningService.findByMovieId(movieId));
    }

    @GetMapping("/by-hall/{hallId}")
    public ResponseEntity<List<Screening>> getScreeningsByHall(@PathVariable Long hallId) {
        return ResponseEntity.ok(screeningService.findByCinemaHallId(hallId));
    }


    @PostMapping
    public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) {
        return ResponseEntity.ok(screeningService.save(screening));
    }

}
