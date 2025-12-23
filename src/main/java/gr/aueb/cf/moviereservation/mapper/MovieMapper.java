package gr.aueb.cf.moviereservation.mapper;

import gr.aueb.cf.moviereservation.dto.MovieCreateDTO;
import gr.aueb.cf.moviereservation.dto.MovieReadDTO;
import gr.aueb.cf.moviereservation.model.Movie;



public class MovieMapper {

    // Entity Movie to DTO
    public static MovieReadDTO toReadDTO(Movie movie) {
        return new MovieReadDTO(
                movie.getUuid(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getGenre());
    }

    // DTO to Entity Movie
    public static Movie toEntity(MovieCreateDTO dto) {
        return  Movie.builder()
                .title(dto.title())
                .description(dto.description())
                .duration(dto.duration())
                .genre(dto.genre())
                .isActive(true)
                .build();
    }
}
