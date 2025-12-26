package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.authentication.CustomUserDetailsService;
import gr.aueb.cf.moviereservation.dto.MovieCreateDTO;
import gr.aueb.cf.moviereservation.dto.MovieReadDTO;
import gr.aueb.cf.moviereservation.mapper.MovieMapper;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieReadDTO>> getAllMovies() {
        List<MovieReadDTO> movies = movieService.findAllMovies()
                .stream()
                .map(MovieMapper::toReadDTO)
                .toList();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<MovieReadDTO> getMovieByUuid(@PathVariable String uuid) {
        return movieService.findByUuid(uuid)
                .map(MovieMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieReadDTO> createMovie(@RequestBody MovieCreateDTO dto) {
        Movie movie = MovieMapper.toEntity(dto);
        Movie saved = movieService.save(movie);
        return ResponseEntity.ok(MovieMapper.toReadDTO(saved));
    }

}
