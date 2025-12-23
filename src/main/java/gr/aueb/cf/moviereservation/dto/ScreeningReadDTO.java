package gr.aueb.cf.moviereservation.dto;

import java.time.LocalDateTime;

public record ScreeningReadDTO (
        Long id,
        String uuid,
        String movieTitle,
        String cinemaHallName,
        LocalDateTime screeningDateTime,
        Double price,
        Boolean isActive
) {}
