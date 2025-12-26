package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.dto.CinemaHallCreateDTO;
import gr.aueb.cf.moviereservation.dto.CinemaHallReadDTO;
import gr.aueb.cf.moviereservation.mapper.CinemaHallMapper;
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
    public ResponseEntity<List<CinemaHallReadDTO>> getAllHalls() {
        List<CinemaHallReadDTO> halls = cinemaHallService.findAll().stream().map(CinemaHallMapper::toReadDTO).toList();
        return ResponseEntity.ok(halls);
    }

    @PostMapping
    public ResponseEntity<CinemaHallReadDTO> createHall(@RequestBody CinemaHallCreateDTO dto) {
        CinemaHall hall = CinemaHallMapper.toEntity(dto);
        CinemaHall saved = cinemaHallService.save(hall);
        return ResponseEntity.ok(CinemaHallMapper.toReadDTO(saved));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaHallReadDTO> getHallById(@PathVariable Long id) {
        return cinemaHallService.findById(id)
                .map(CinemaHallMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
