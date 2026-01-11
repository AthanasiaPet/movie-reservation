package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.core.exceptions.ResourceNotFoundException;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> findAllMovies() {
        log.debug("Fetching all movies");
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Movie findByUuid(String uuid) {
        log.debug("Fetching movie with uuid={}", uuid);

         return movieRepository.findByUuid(uuid).orElseThrow(() -> {
             log.warn("Movie not found with uuid={}", uuid);
             return new ResourceNotFoundException("Movie", "uuid", uuid);
         });
    }

    public Movie save(Movie movie) {

        if (movieRepository.existsByTitle(movie.getTitle())) {
            log.warn("Attempt to create movie with existing title={}", movie.getTitle());
            throw new ResourceAlreadyExistsException("Movie", "title", movie.getTitle());
        }

        Movie savedMovie = movieRepository.save(movie);
        log.info("Movie with id={} and title={} created successfully", savedMovie.getId(), savedMovie.getTitle());

        return savedMovie;

    }


}
