package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.ScreeningCreateDTO;
import gr.aueb.cf.moviereservation.dto.ScreeningReadDTO;
import gr.aueb.cf.moviereservation.mapper.ScreeningMapper;
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
    public ResponseEntity<List<ScreeningReadDTO>> getAllScreenings() {
        List<ScreeningReadDTO> screenings = screeningService.findAll().stream().map(ScreeningMapper::toReadDTO).toList();
        return ResponseEntity.ok(screenings);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<ScreeningReadDTO> getScreeningByUuid(@PathVariable String uuid) {
        return screeningService.findByUuid(uuid)
                .map(ScreeningMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-movie/{movieId}")
    public ResponseEntity<List<ScreeningReadDTO>> getScreeningsByMovie(@PathVariable Long movieId) {
        List<ScreeningReadDTO> screenings = screeningService.findByMovieId(movieId).stream().map(ScreeningMapper::toReadDTO).toList();
        return ResponseEntity.ok(screenings);
    }

    @GetMapping("/by-hall/{hallId}")
    public ResponseEntity<List<ScreeningReadDTO>> getScreeningsByHall(@PathVariable Long hallId) {
       List<ScreeningReadDTO> screenings = screeningService.findByCinemaHallId(hallId).stream().map(ScreeningMapper::toReadDTO).toList();
       return ResponseEntity.ok(screenings);
    }

    @PostMapping
    public ResponseEntity<ScreeningReadDTO> createScreening(@RequestBody ScreeningCreateDTO dto) {
        Screening saved = screeningService.createScreening(dto);
        return ResponseEntity.ok(ScreeningMapper.toReadDTO(saved));
    }




}
