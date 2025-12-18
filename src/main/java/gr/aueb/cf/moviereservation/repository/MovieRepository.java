package gr.aueb.cf.moviereservation.repository;

import gr.aueb.cf.moviereservation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByUuid(String uuid);
    Optional<Movie> findByTitle(String title);


}
