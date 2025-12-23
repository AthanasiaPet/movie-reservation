package gr.aueb.cf.moviereservation.dto;

public record CinemaHallReadDTO (
        Long id,
        String uuid,
        String hallName,
        Integer capacity,
        Boolean isActive
) {}
