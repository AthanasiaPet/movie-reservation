package gr.aueb.cf.moviereservation.dto;

import java.time.LocalDateTime;

public record ScreeningCreateDTO (
        Long movieId,
        Long cinemaHallId,
        LocalDateTime screeningDateTime,
        Double price
) {}
