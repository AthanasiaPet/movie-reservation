package gr.aueb.cf.moviereservation.service;

import gr.aueb.cf.moviereservation.core.exceptions.ResourceAlreadyExistsException;
import gr.aueb.cf.moviereservation.model.Movie;
import gr.aueb.cf.moviereservation.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findAllMovies_returnsMoviesList() {

        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieRepository.findAll()).thenReturn(List.of(movie));


        List<Movie> movies = movieService.findAllMovies();

        assertEquals(1, movies.size());
        assertEquals("Inception", movies.get(0).getTitle());
        verify(movieRepository).findAll();
    }

    @Test
    void findByUuid_returnsMovieWhenExists() {

        Movie movie = new Movie();
        movie.setUuid("uuid-123");

        when(movieRepository.findByUuid("uuid-123"))
                .thenReturn(Optional.of(movie));


        Optional<Movie> result = movieService.findByUuid("uuid-123");


        assertTrue(result.isPresent());
        assertEquals("uuid-123", result.get().getUuid());
        verify(movieRepository).findByUuid("uuid-123");
    }

    @Test
    void save_throwsException_whenMovieTitleAlreadyExists() {

        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieRepository.existsByTitle("Inception"))
                .thenReturn(true);


        assertThrows(ResourceAlreadyExistsException.class,
                () -> movieService.save(movie));

        verify(movieRepository).existsByTitle("Inception");
        verify(movieRepository, never()).save(any());
    }
}
