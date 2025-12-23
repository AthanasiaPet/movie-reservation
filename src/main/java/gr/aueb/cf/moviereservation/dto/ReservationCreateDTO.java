package gr.aueb.cf.moviereservation.dto;

public record  ReservationCreateDTO (
        Long screeningId,
        String seatNumber
) {}
