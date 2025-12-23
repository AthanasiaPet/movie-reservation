package gr.aueb.cf.moviereservation.dto;

import gr.aueb.cf.moviereservation.core.enums.ReservationStatus;

public record ReservationReadDTO (
        Long id,
        String uuid,
        String movieTitle,
        String cinemaHallName,
        String seatNumber,
        ReservationStatus status,
        Boolean isActive
) {}
