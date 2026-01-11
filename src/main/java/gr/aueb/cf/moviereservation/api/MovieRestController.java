package gr.aueb.cf.moviereservation.api;

import gr.aueb.cf.moviereservation.authentication.CustomUserDetailsService;
import gr.aueb.cf.moviereservation.dto.MovieCreateDTO;
import gr.aueb.cf.moviereservation.dto.MovieReadDTO;
import gr.aueb.cf.moviereservation.mapper.MovieMapper;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class MovieRestController {

    private final MovieService movieService;

    @Operation(
            summary = "Get all movies",
            description = "Returns a list of all available movies."
    )

    @GetMapping
    public ResponseEntity<List<MovieReadDTO>> getAllMovies() {
        List<MovieReadDTO> movies = movieService.findAllMovies()
                .stream()
                .map(MovieMapper::toReadDTO)
                .toList();

        return ResponseEntity.ok(movies);
    }

    @Operation(
            summary = "Get movie by UUID",
            description = "Returns a movie identified by its UUID."
    )

    @GetMapping("/{uuid}")
    public ResponseEntity<MovieReadDTO> getMovieByUuid(@PathVariable String uuid) {
        Movie movie = movieService.findByUuid(uuid);

        return ResponseEntity.ok(
                MovieMapper.toReadDTO(movie)
        );
    }

    @Operation(
            summary = "Create a movie",
            description = "Creates a new movie. Requires authentication."
    )

    @PostMapping
    public ResponseEntity<MovieReadDTO> createMovie(@RequestBody MovieCreateDTO dto) {
        Movie movie = MovieMapper.toEntity(dto);
        Movie saved = movieService.save(movie);
        return ResponseEntity.ok(MovieMapper.toReadDTO(saved));
    }

}
