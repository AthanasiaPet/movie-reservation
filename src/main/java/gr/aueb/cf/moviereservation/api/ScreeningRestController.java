package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.ScreeningCreateDTO;
import gr.aueb.cf.moviereservation.dto.ScreeningReadDTO;
import gr.aueb.cf.moviereservation.mapper.ScreeningMapper;
import gr.aueb.cf.moviereservation.model.Screening;
import gr.aueb.cf.moviereservation.service.ScreeningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class ScreeningRestController {

    private final ScreeningService screeningService;

    @Operation(
            summary = "Get all screenings",
            description = "Returns a list of all available movie screenings."
    )

    @GetMapping
    public ResponseEntity<List<ScreeningReadDTO>> getAllScreenings() {
        List<ScreeningReadDTO> screenings = screeningService.findAll().stream().map(ScreeningMapper::toReadDTO).toList();
        return ResponseEntity.ok(screenings);
    }

    @Operation(
            summary = "Get screening by UUID",
            description = "Returns a screening identified by its UUID."
    )


    @GetMapping("/{uuid}")
    public ResponseEntity<ScreeningReadDTO> getScreeningByUuid(@PathVariable String uuid) {
        return screeningService.findByUuid(uuid)
                .map(ScreeningMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get screenings by movie",
            description = "Returns all screenings for a specific movie."
    )

    @GetMapping("/by-movie/{movieId}")
    public ResponseEntity<List<ScreeningReadDTO>> getScreeningsByMovie(@PathVariable Long movieId) {
        List<ScreeningReadDTO> screenings = screeningService.findByMovieId(movieId).stream().map(ScreeningMapper::toReadDTO).toList();
        return ResponseEntity.ok(screenings);
    }

    @Operation(
            summary = "Get screenings by cinema hall",
            description = "Returns all screenings for a specific cinema hall."
    )

    @GetMapping("/by-hall/{hallId}")
    public ResponseEntity<List<ScreeningReadDTO>> getScreeningsByHall(@PathVariable Long hallId) {
       List<ScreeningReadDTO> screenings = screeningService.findByCinemaHallId(hallId).stream().map(ScreeningMapper::toReadDTO).toList();
       return ResponseEntity.ok(screenings);
    }

    @Operation(
            summary = "Create a screening",
            description = "Creates a new screening for a specific movie and cinema hall. Requires authentication."
    )

    @PostMapping
    public ResponseEntity<ScreeningReadDTO> createScreening(@RequestBody ScreeningCreateDTO dto) {
        Screening saved = screeningService.createScreening(dto);
        return ResponseEntity.ok(ScreeningMapper.toReadDTO(saved));
    }




}
