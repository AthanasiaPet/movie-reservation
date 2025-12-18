package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findByUuid(String uuid) {
        return movieRepository.findByUuid(uuid);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }




}
