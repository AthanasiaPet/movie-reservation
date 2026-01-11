package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.CinemaHallCreateDTO;
import gr.aueb.cf.moviereservation.dto.CinemaHallReadDTO;
import gr.aueb.cf.moviereservation.mapper.CinemaHallMapper;
import gr.aueb.cf.moviereservation.model.CinemaHall;
import gr.aueb.cf.moviereservation.service.CinemaHallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema-halls")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CinemaHallRestController {

    private final CinemaHallService cinemaHallService;

    @Operation(
            summary = "Get all cinema halls",
            description = "Returns a list of all available cinema halls."
    )

    @GetMapping
    public ResponseEntity<List<CinemaHallReadDTO>> getAllHalls() {
        List<CinemaHallReadDTO> halls = cinemaHallService.findAll().stream().map(CinemaHallMapper::toReadDTO).toList();
        return ResponseEntity.ok(halls);
    }

    @Operation(
            summary = "Create a cinema hall",
            description = "Creates a new cinema hall. Requires authentication."
    )

    @PostMapping
    public ResponseEntity<CinemaHallReadDTO> createHall(@RequestBody CinemaHallCreateDTO dto) {
        CinemaHall hall = CinemaHallMapper.toEntity(dto);
        CinemaHall saved = cinemaHallService.save(hall);
        return ResponseEntity.ok(CinemaHallMapper.toReadDTO(saved));

    }

    @Operation(
            summary = "Get cinema hall by ID",
            description = "Returns a cinema hall identified by its ID."
    )

    @GetMapping("/{id}")
    public ResponseEntity<CinemaHallReadDTO> getHallById(@PathVariable Long id) {
        CinemaHall hall = cinemaHallService.findById(id);

        return ResponseEntity.ok(
                CinemaHallMapper.toReadDTO(hall)
        );
    }


}
